# ssm-spring
```shell
# Spring官网：https://spring.io/projects

# Spring IOC/DI(核心)：组件管理、依赖注入、三种配置方式  -- spring01/02.xml & SpringConfig & SpringTest01/02

# Spring AOP：代理模式 -- spring02.xml & proxy & advice

# Spring TX：声明式事务、事务注解、事务属性  -- spring02.xml & UserController & UserServiceImpl & CheckoutServiceImpl
```

# ssm-mybatis
```shell
# MyBatis官网：https://mybatis.org/mybatis-3/zh_CN/index.html

# MyBatis单表查询：接收参数方式、输入参数类型、返回结果类型 -- UserMapper

# MyBatis多表查询：实体类设计、resultMap标签、分步查询延迟加载 -- EmpMapper("对一") & DeptMapper("对多")

# MyBatis动态sql：where、if、foreach标签  -- BookMapper

# MyBatis扩展：分页插件、缓存、逆向工程插件MybatisX

# MyBatis-Plus官网：https://baomidou.com/pages/24112f/

# MyBatis-Plus核心：实体类注解、条件构造器  -- User & MybatisPlusTest & IService<T> & BaseMapper<T>

# MyBatis-Plus扩展：分页插件、逻辑删除、乐观锁插件、防止恶意攻击插件  -- MybatisPlusTest
```

# ssm-springmvc
```shell
# SpringMVC官网：https://docs.spring.io/spring-framework/reference/web/webmvc.html

# SpringMVC 核心组件和调用流程  -- WebAppInitializer(web.xml) & WebMvcConfiguration(springmvc.xml) & HelloController

# SpringMVC 简化参数接收：路径设计、参数接收(重点)、请求头接收、cookie接收  -- MappingController & ParamController

# SpringMVC 简化数据响应：模板页面、转发和重定向、返回JSON(重点)、静态资源  -- ViewController

# RESTFUL 风格设计：主要作用、具体规范、请求方式和请求参数  -- RestfulController

# SpringMVC 功能扩展：全局异常处理、拦截器、参数校验注解  -- GlobalExceptionHandler & MyHandlerInterceptor & MappingController
```

# ssm-integration
```shell
# ssm整合就是将所有组件都交给IOC容器管理
# 1.创建maven工程导入所有依赖
# 2.控制层配置类,整合SpringMVC(Controller、HandlerMapping、HandlerAdapter、ViewResolver、DefaultServlet...)
# 3.业务层配置类,整合Spring(Service、AOP、TX)
# 4.持久层配置类,整合Mybatis(DataSource、SqlSessionFactory、Mapper)
# 5.IOC容器初始化配置类

# 早期是单体项目,只写一次配置即可
# 现在都是微服务,将单体项目进行服务化拆分,有一个模块就拆成一个服务,每个服务都是独立项目,最终会有N个服务要写N次配置

# SpringBoot底层整合了大量自动配置,大大简化Spring项目的开发,只需关注业务逻辑即可
```