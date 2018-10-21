package com.ivo.activiticlient.biz;

import com.ivo.activiticlient.domain.Department;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.domain.Handler;
import com.ivo.activiticlient.domain.Responsibility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Service
@Transactional
public class BizService extends HrService{

    //根据表名和条件获取数据[通用]
    public List<?> getItems(String tableName, String strConditon){
        return findAllByConditon(tableName,strConditon);
    }
    //根据表名获取表内有效数据[通用]
    public List<?> getList(String tableName){
        List<?> list = findAllByConditon(tableName, "validFlag = 1");
        if(list != null && list.size() > 0)
            return list;
        else return null;
    }

    //根据员工工号获取直属主管工号
    public String getLeader(String employee_FK){
        String leader = "";
        Employee emp = getEmp(employee_FK);
        if(emp != null){
            Department dept = emp.getDepartment();
            if(dept != null){
                Employee deptHead = dept.getDeptHead();
                if(deptHead != null){
                    while(deptHead.getEmployee_ID().equals(employee_FK)){
                        deptHead = deptHead.getDepartment().getParentDept().getDeptHead();
                    }
                    leader = deptHead.getEmployee_ID();
                }

            }

        }
        return leader;
    }

    //根据部门编号获取部门主管
    public String getDeptHead(String dept_ID){
        String leader = "";
        Department dept = getDept(dept_ID);
        if(dept != null){
            while(dept.getDeptHead() == null){
                dept = dept.getParentDept();
            }
            leader = dept.getDeptHead().getEmployee_ID();
        }
        return leader;
    }

    //根据工号获取部门
    public String getDeptIDByEmp(String emp_ID){
        String dept_ID = "";
        Employee emp  = getEmp(emp_ID);

        if(emp != null){
            dept_ID = emp.getDepartment().getDepartment_ID();
        }
        return dept_ID;
    }

    //根据部门ID获取部门下所有的员工
    @SuppressWarnings("unchecked")
    public List<?> getEmpsByDeptId(String dept_ID){

        List<Employee> emps = findAllByConditon("Employee", "validFlag = 1 and department.department_ID = '"+dept_ID+"'");

        List<?> depts = getChildDepts(dept_ID);
        if(depts != null){
            for(int i = 0; i < depts.size(); i++){
                Department dept = (Department)depts.get(i);
                List<Employee> emps_ = findAllByConditon("Employee", "validFlag = 1 and department.department_ID = '"+dept.getDepartment_ID()+"'");
                if(emps != null && emps.size() > 0)
                    emps.addAll(emps_);
            }
        }
        if(emps != null && emps.size() > 0){
            return emps;
        }
        return null;
    }

    //根据部门ID获取子部门
    public List<?> getChildDepts(String dept_ID){
        List<?> depts = findAllByConditon("Department", "validFlag = 1 and parentDept.department_ID = '"+dept_ID+"'");

        if(depts != null && depts.size() > 0){
            return depts;
        }
        return null;
    }

    //根据签核单简写、节点、类型到SYS_O_Handler表中获取处理人
    @SuppressWarnings("unchecked")
    public String getHanders(String sid, String node, int type) {
        StringBuffer sb = new StringBuffer();
        sb.append(" sid = '").append(sid).append("'");
        sb.append(" and node = '").append(node).append("'");
        sb.append(" and type = ").append(type);
        sb.append(" and validflag =1 ");
        List<Handler> empList = (List<Handler>) this.getItems("Handler", sb.toString());
        StringBuffer handlers = new StringBuffer();
        for(int i=0; i<empList.size(); i++){
            Employee e = empList.get(i).getEmployee();
            if(i==0){
                handlers.append(e.getDepartment().getDepartment_ID()).append("/").append(e.getEmployee_ID());
                continue;
            }
            handlers.append(",").append(e.getDepartment().getDepartment_ID()).append("/").append(e.getEmployee_ID());
        }

        return handlers.toString();
    }

    //根据Employee和BIZ获取部门，根据BIZ获取助理职的部门
    public String getDept(Employee employee,String biz){
        boolean attspFlag = false;
        boolean attFlag = false;
        String department_ID = employee.getDepartment().getDepartment_ID();
        List<?> list = findAllByConditon("Responsibility", "employee.employee_ID = '"+employee.getEmployee_ID()+"' " +
                "and validFlag = 1 and position.position_ID in('30000903','30000309','30001177')");
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                Responsibility objRs = (Responsibility)list.get(i);
                if(objRs.getPosition().getPosition_ID().equals("30001177")){
                    attFlag = true;
                }else{
                    attspFlag = true;
                }
            }
        }
        if(attFlag && biz.equals("")){
            Department objDept = (Department)getDao().getObject(Department.class, department_ID);
            department_ID = objDept.getParentDept().getDepartment_ID();
        }else if(attFlag && biz.equals("SR")){
            List<?> objs = getDao().sqlQuery("SELECT dbo.GetDeptByLevelToOrg('"+department_ID+"',6) parentDept");
            if(objs.size()>0) {
                Map<?,?> map = (Map<?,?>)objs.get(0);
                department_ID = (String)map.get("parentDept");
            }
        }
        if(attspFlag) department_ID = "10000000";

        return department_ID;
    }

    //判断人员是否拥有[助理]职位
    public boolean getAttFlag(String drafter){
        List<?> list = findAllByConditon("Responsibility", "employee.employee_ID = '"+drafter+"' " +
                " and validFlag = 1 and position.position_ID in('30001177')");
        if(list != null && list.size() > 0) return true;
        else return false;
    }

    //根据Department和level获取部门主管
    public Employee getDeptHead(Department draftGroup, int level){
        String deptHead_FK = "";
        Employee head = null;
        List<?> list = getDao().sqlQuery("select " +
                "dbo.GetDeptHead(dbo.GetDeptByLevelToOrg('"+draftGroup.getDepartment_ID()+"',"+level+")) deptHead_FK");
        if(list.size()>0) {
            Map<?,?> map = (Map<?,?>)list.get(0);
            deptHead_FK = (String)map.get("deptHead_FK");
            head = getDao().getObject(Employee.class, deptHead_FK);
        }
        return head;
    }
//    //获取ECRS邮件发送人员列表
//    @SuppressWarnings("unchecked")
//    public List<MailGroup> getPlGroup() {
//        List<MailGroup> list = findAllByConditon("MailGroup", "remark='plCheck' and validFlag = 1");
//        return list;
//    }

    /**
     * 根据部门号判断是否属于董总办中心
     * @param gid
     * @return boolean
     */
    public boolean getBACFlag(String gid) {
        String hql = "select d from Department d where d.deptPath like '%10000828%' and d.validFlag=1 and d.department_ID=?";
        List<?> list = find(hql, gid);
        if (list.size() > 0)
            return true;
        return false;
    }

    /**
     * 根据position ID获取员工 ,获取第一笔
     * @param res_ID
     * @return
     */
    public String getEmpByPosition(String position_ID){
        String emp = "";
        Responsibility res = (Responsibility)findByConditon("Responsibility",
                "position.position_ID = '"+position_ID+"' and validFlag = 1");
        if (res != null) emp = res.getEmployee().getEmployee_ID();

        return emp;
    }
}
