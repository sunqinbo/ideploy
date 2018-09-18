package io.ideploy.deployment.cmd;

import com.google.common.collect.Lists;
import io.ideploy.deployment.base.ApiCode;
import io.ideploy.deployment.common.enums.DeployResult;
import io.ideploy.deployment.common.util.IpAddressUtils;
import io.ideploy.deployment.exception.ServiceException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.util.Assert;

/**
 * 功能：命令工具类
 * 详细：
 *
 * @author linyi, 2017/2/15.
 */
public class CommandUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommandUtil.class);

    private static final Pattern HOST_LINE_PATTERN=  Pattern.compile("^(([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})\\s*,+");


    /**
     * 执行命令并返回结果
     * @param args
     * @return result.successType=DeployResult.SUCCESS 为 true
     */
    public static AnsibleCommandResult execAnsible(String[] args) {
        AnsibleCommand ansibleCommand = new AnsibleCommand();
        CommandResult commandResult = ansibleCommand.exec(args);
        AnsibleCommandResult result = new AnsibleCommandResult();
        result.setSuccess(commandResult.isSuccess());
        result.setSuccessType((commandResult.isSuccess() ? DeployResult.SUCCESS : DeployResult.FAILURE));
        result.setMessage(commandResult.getMessage());
        result.setExitValue(commandResult.getExitValue());
        result.setErrorMessage(commandResult.getErrorMessage());
        return result;
    }


    /**
     * 批量 ping 服务器
     * @param hosts 服务器列表，不能为空
     * @return success=全部成功
     */
    public static AnsibleCommandResult ansiblePing(List<String> hosts) {
        AnsibleCommandResult result = new AnsibleCommandResult();
        if (CollectionUtils.isEmpty(hosts)) {
            result.setMessage("hosts不能为空");
            return result;
        }

        File tempFile = generateHostFile(hosts, result);
        if (tempFile == null) {
            result.setMessage("生成host文件失败");
            return result;
        }

        String[] args = {"-i", tempFile.getAbsolutePath(), "all", "-m", "ping", "-T", "10"};
        CommandResult commandResult = new AnsibleCommand().exec(args);

        return AnsibleCommand.parse(commandResult.getMessage(), hosts);
    }

    private static File generateHostFile(List<String> hosts, AnsibleCommandResult result) {
        FileWriter writer = null;
        File tempFile = null;
        try {
            tempFile = createTempFile(AnsibleCommand.ANSIBLE);
            writer = new FileWriter(tempFile);

            writer.write("[all]\n");
            writer.write(StringUtils.join(hosts, "\n"));
            writer.flush();

        } catch (IOException e) {
            result.setMessage(e.getMessage());
            logger.error("创建临时文件出错", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return tempFile;
    }

    /**
     * 创建一个临时文件
     * @param suffix 文件后缀
     * @return
     * @throws IOException
     */
    private static File createTempFile(String suffix) throws IOException {
        return File.createTempFile("deploy_", suffix);
    }

    public static void main(String[] args) {
        AnsibleCommandResult ansibleCommandResult = CommandUtil.ansiblePing(Lists.newArrayList("123.56.158.175", "1.1.1.1"));
        System.out.println(ansibleCommandResult);
    }

    public static String[] ansibleCmdArgs(String[] args, int hostIndex){
        if(args == null || args.length < hostIndex){
            return args;
        }
        String host = args[hostIndex];
        if(HOST_LINE_PATTERN.matcher(host).matches()){
            String ip = host.substring(0, host.length() - 1);
            if(IpAddressUtils.isLocalIP(ip)){
                String[]tmpArgs = Arrays.copyOf(args, args.length + 2);
                tmpArgs[args.length] = "--connection";
                tmpArgs[args.length + 1] = "local";

                return tmpArgs;
            }
        }

        return  args;
    }
}
