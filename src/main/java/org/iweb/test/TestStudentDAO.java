package org.iweb.test;



import org.iweb.dao.StudentDAO;
import org.iweb.dao.StudentDAOimpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 对StudentDAO接口的测试
 * @Test 不依赖main方法 将指定方法标记为测试方法
 * @Before 在所有测试方法运行之前 一般用于初始化
 * @After 在所有测试方法运行之后 一般用于资源回收
 * @author xiwei
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private StudentDAO studentDAO;
    @Before
    public void init(){
        studentDAO = new StudentDAOimpl();
    }
    @Test
    public void testFindById(){
        Assert.assertNotNull(studentDAO.getStudentById(1));
    }
    @Test
    public void testCount(){
        Assert.assertEquals(29L,(long)studentDAO.count());
    }
    @Test
    public void testFindAll(){
        Assert.assertNotNull(studentDAO.findAllStudent());
    }
    @Test
    public void testFindByNameLike(){
        Assert.assertNotNull(studentDAO.findStudentByNameLike("z"));
    }
    @Test
    public void testFindStudentByNameLikeWithLimit(){
        Assert.assertNotNull(studentDAO.findStudentByNameLikeWithLimit("z",0,100));
    }
    @Test
    public void testFindWithLimit(){
        Assert.assertNotNull(studentDAO.findWithLimit(0,5));
    }
}
