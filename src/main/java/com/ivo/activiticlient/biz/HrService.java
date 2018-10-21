package com.ivo.activiticlient.biz;

import com.ivo.activiticlient.common.dao.HibernateCaller;
import com.ivo.activiticlient.domain.Department;
import com.ivo.activiticlient.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Service
@Transactional
public class HrService{

    @Autowired
    private HibernateCaller dao;

    public HrService() {}

    public HibernateCaller getDao() {
        return dao;
    }
    public void setDao(HibernateCaller dao) {
        this.dao = dao;
    }

    public void save(Object obj) {
        dao.saveOrUpdateObject(obj);
    }

    public void delete(Object obj) {
        dao.removeObject(obj);
    }

    @SuppressWarnings("rawtypes")
    public List find(String hql) {
        return dao.find(hql);
    }

    @SuppressWarnings("rawtypes")
    public Object findByhql(String hql) {
        List objs = dao.find(hql);
        if(objs.size() > 0){
            return objs.get(0);
        }else{
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List find(String hql, Object... values) {
        return dao.find(hql, values);
    }

    @SuppressWarnings("rawtypes")
    public List findAll(String tableName) {
        List objs = dao.find("from "+tableName+" where validFlag=1");
        return objs;
    }

    @SuppressWarnings("rawtypes")
    public List findSortAsc(String tableName, String conditon, String column) {
        List objs = dao.find("from "+tableName+" where "+ conditon +" order by "+column+"");
        return objs;
    }

    @SuppressWarnings("rawtypes")
    public List findSortDesc(String tableName, String conditon, String column) {
        List objs = dao.find("from "+tableName+" where "+ conditon +" order by "+column+" desc");
        return objs;
    }

    @SuppressWarnings("rawtypes")
    public Object findByID(String tableName, String tableID, String id) {
        List objs = dao.find("from "+tableName+" where validFlag=1 and "+tableID+"=?",id);
        if(objs.size()!=0){
            return objs.get(0);
        }else{
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAllByID(String tableName,String tableID,String id) {
        List objs = dao.find("from "+tableName+" where validFlag=1 and "+tableID+"=?",id);
        if(objs.size()!=0){
            return objs;
        }else{
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAllByIDDESC(String tableName,String tableID,String id,String datetime) {
        List objs = dao.find("from "+tableName+" where validFlag=1 and "+tableID+"=? order by "+datetime+" DESC",id);
        if(objs.size()!=0){
            return objs;
        }else{
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAllByConditon(String tableName,String strConditon) {
        List objs = dao.find("from "+tableName+" where "+strConditon+"");
        return objs;
    }

    @SuppressWarnings("rawtypes")
    public Object findByConditon(String tableName, String condtion) {
        List objs = dao.find("from "+tableName+" where "+condtion+"");
        if(objs.size()!=0){
            return objs.get(0);
        }else{
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAllByConditon(String tableName,String strConditon,String strOrder) {
        List objs = dao.find("from "+tableName+" where "+strConditon+" order by "+strOrder);
        return objs;
    }

    @SuppressWarnings("rawtypes")
    public Object findDtblTopOne(String tableName,String strConditon,String strOrder) {
        List objs = dao.find("from "+tableName+" where validFlag=1 and "+strConditon+" order by "+strOrder +" DESC");
        if(objs.size()!=0){ return objs.get(0);}
        return  null;
    }

    @SuppressWarnings("rawtypes")
    public List findByLimit(int limit, String hql, Object... values) {
        return dao.findByPage(1, limit, hql, values);
    }

    @SuppressWarnings("rawtypes")
    public List findByCount(String hql, int maxResult, Object... values) {
        return dao.find(hql, maxResult, values);
    }

    @SuppressWarnings("rawtypes")
    public List execProc(String procName, Object... values) {
        List list =  dao.execProc("{call "+procName+"}", values);
        if(list.size()!=0){
            return list;
        }else{
            return null;
        }
    }

    public Employee getEmp(String employee_ID){
        Employee objEmp = (Employee)findByID("Employee", "Employee_ID", employee_ID);
        if(objEmp != null) return objEmp;
        else return null;
    }

    public Employee getEmp_(String employee_ID){
        Employee objEmp = (Employee)findByConditon("Employee", "Employee_ID = '"+employee_ID+"'");
        if(objEmp != null) return objEmp;
        else return null;
    }

    public Department getDept(String dept_ID){
        Department objDept = (Department)findByConditon("Department", "Department_ID = '"+dept_ID+"'");
        if(objDept != null) return objDept;
        else return null;
    }


    public boolean getAttspFlag(String drafter){
        List<?> list = findAllByConditon("Responsibility", "employee.employee_ID = '"+drafter+"' " +
                "and validFlag = 1 and position.position_ID in('30000903','30000309')");
        if(list != null && list.size() > 0) return true;
        else return false;
    }

    public boolean getShowFlag(String dept){
        List<?> list = findAllByConditon("Department", "department_ID = '"+dept+"' " +
                "and (PATINDEX('%10000791%',deptPath) > 1 or PATINDEX('%10000811%',deptPath) > 1 or PATINDEX('%10000550%',deptPath) > 1 or PATINDEX('%10000753%',deptPath) > 1)");
        if(list != null && list.size() > 0) return true;
        else return false;
    }
}
