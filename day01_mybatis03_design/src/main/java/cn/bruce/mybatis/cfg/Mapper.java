package cn.bruce.mybatis.cfg;

/**
 * @author Bruce
 * @create 2020-09-10 14:14
 * 用于封装执行SQL语句和结果类型的全限定类名
 */
public class Mapper {

    /**
     * 查询的SQL语句
     */
    private String queryString;

    /**
     * 实体类的全限定类名
     */
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
