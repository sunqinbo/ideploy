package io.ideploy.deployment.admin.po.project;

import io.ideploy.deployment.admin.vo.server.ServerGroup;
import io.ideploy.deployment.common.Constants;
import io.ideploy.deployment.common.enums.ModuleType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 模块详情
 * 
 * <pre>
 *     自动生成代码: 表名 t_project_module, 日期: 2017-01-12
 *     module_id <PK>          int(11)
 *     module_name_zh    varchar(40)
 *     module_name       varchar(50)
 *     module_type       tinyint(4)
 *     src_path          varchar(200)
 *     pre_shell         varchar(500)
 *     post_shell        varchar(500)
 *     log_name          varchar(200)
 *     repo_type         tinyint(4)
 *     repo_url          varchar(200)
 *     create_time       datetime(19)
 *     compile_shell     varchar(200)
 *     stop_shell        varchar(200)
 *     restart_shell     varchar(200)
 *     project_id        int(11)
 *     need_audit         tinyint(2)
 *     svn_account       varchar(80)
 *     svn_password      varchar(80)
 * </pre>
 */
public class ProjectModulePO implements Serializable {

	private static final long serialVersionUID = -3074457346781788250L;

	/** moduleId */
	private int moduleId;

	/** 模块中文名称 */
	private String moduleNameZh;


	/** 模块名称 */
	private String moduleName;

	/**
	 * 模块类型，0代表web项目 1代表dubbo服务
	 * @see ModuleType
	 */
	 private short moduleType = ModuleType.WEB_PROJECT.getValue();

	 /** SVN上的目录，比如 service-impl/target/*.jar */
	private String srcPath;

	/** 部署自定变量 **/
	private String deployArgs="";

    /** 开始部署执行操作 **/
    private String preDeploy="";

    /** 部署完成执行操作 **/
    private String postDeploy="";

	/** 发布前执行的shell */
	private String preShell = "";

	/** 发布后执行的shell */
	private String postShell = "";

	/** 日志名称，读取日志并返回 */
	private String logName = "";

	/** 版本管理地址，比如：svn://a.b.com/project/tags */
	private String repoUrl;

	/** 创建时间 */
	private Date createTime;

	/** 编译脚本 */
	private String compileShell;

	/** 停止服务脚本 */
	private String stopShell = "";

	/** 重启服务脚本 */
	private String restartShell;

	/** 项目id */
	private int projectId;

	/** 是否需要审核，0不需要，1需要 */
	private short needAudit = Constants.TRUE;

    /***
     * 仓库认证ID
     */
	private int repoAuthId;

	/**
	 * 服务器组
	 */
	private List<ServerGroup> serverGroups;

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getModuleId() {
		return moduleId;
	}

    public void setModuleNameZh(String moduleNameZh) {
		this.moduleNameZh = moduleNameZh;
	}

	public String getModuleNameZh() {
		return moduleNameZh;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleType(short moduleType) {
		this.moduleType = moduleType;
	}

	public short getModuleType() {
		return moduleType;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getSrcPath() {
		return srcPath;
	}

    public String getDeployArgs() {
        return deployArgs;
    }

    public void setDeployArgs(String deployArgs) {
        this.deployArgs = deployArgs;
    }

    public void setPreShell(String preShell) {
		this.preShell = preShell;
	}

	public String getPreShell() {
		return preShell;
	}

	public void setPostShell(String postShell) {
		this.postShell = postShell;
	}

	public String getPostShell() {
		return postShell;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogName() {
		return logName;
	}

	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}

	public String getRepoUrl() {
		return repoUrl;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCompileShell(String compileShell) {
		this.compileShell = compileShell;
	}

	public String getCompileShell() {
		return compileShell;
	}

	public void setStopShell(String stopShell) {
		this.stopShell = stopShell;
	}

	public String getStopShell() {
		return stopShell;
	}

	public void setRestartShell(String restartShell) {
		this.restartShell = restartShell;
	}

	public String getRestartShell() {
		return restartShell;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setNeedAudit(short needAudit) {
		this.needAudit = needAudit;
	}

	public short getNeedAudit() {
		return needAudit;
	}

	public List<ServerGroup> getServerGroups() {
		return serverGroups;
	}

    public String getPreDeploy() {
        return preDeploy;
    }

    public void setPreDeploy(String preDeploy) {
        this.preDeploy = preDeploy;
    }

    public String getPostDeploy() {
        return postDeploy;
    }

    public void setPostDeploy(String postDeploy) {
        this.postDeploy = postDeploy;
    }

    public void setServerGroups(List<ServerGroup> serverGroups) {
		this.serverGroups = serverGroups;
	}

    public int getRepoAuthId() {
        return repoAuthId;
    }

    public void setRepoAuthId(int repoAuthId) {
        this.repoAuthId = repoAuthId;
    }
}
