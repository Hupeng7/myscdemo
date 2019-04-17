# myscdemo
sprint boot init

#### github参考地址
```$xslt
http://blog.didispace.com/spring-boot-learning-1x/
```

#### 多环境配置
我们在开发Spring Boot应用时，通常同一套程序会被应用和安装到几个不同的环境，比如：开发、测试、生产等。其中每个环境的数据库地址、服务器端口等等配置都会不同，如果在为不同环境打包时都要频繁修改配置文件的话，那必将是个非常繁琐且容易发生错误的事。

对于多环境的配置，各种项目构建工具或是框架的基本思路是一致的，通过配置多份不同环境的配置文件，再通过打包命令指定需要打包的内容之后进行区分打包，Spring Boot也不例外，或者说更加简单。

在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识，比如：

application-dev.properties：开发环境
application-test.properties：测试环境
application-prod.properties：生产环境
至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值。

如：spring.profiles.active=test就会加载application-test.properties配置文件内容

下面，以不同环境配置不同的服务端口为例，进行样例实验。

针对各环境新建不同的配置文件application-dev.properties、application-test.properties、application-prod.properties

在这三个文件均都设置不同的server.port属性，如：dev环境设置为1111，test环境设置为2222，prod环境设置为3333

application.properties中设置spring.profiles.active=dev，就是说默认以dev环境设置

测试不同配置的加载

执行java -jar xxx.jar，可以观察到服务端口被设置为1111，也就是默认的开发环境（dev）
执行java -jar xxx.jar --spring.profiles.active=test，可以观察到服务端口被设置为2222，也就是测试环境的配置（test）
执行java -jar xxx.jar --spring.profiles.active=prod，可以观察到服务端口被设置为3333，也就是生产环境的配置（prod）
按照上面的实验，可以如下总结多环境的配置思路：

application.properties中配置通用内容，并设置spring.profiles.active=dev，以开发环境为默认配置
application-{profile}.properties中配置各个环境不同的内容
通过命令行方式去激活不同环境的配置

#### Spring Boot构建RESTful API与单元测试
首先，回顾并详细说明一下在快速入门中使用的@Controller、@RestController、@RequestMapping注解。如果您对Spring MVC不熟悉并且还没有尝试过快速入门案例，建议先看一下快速入门的内容。

@Controller：修饰class，用来创建处理http请求的对象
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
@RequestMapping：配置url映射
下面我们尝试使用Spring MVC来实现一组对User对象操作的RESTful API，配合注释详细说明在Spring MVC中如何映射HTTP请求、如何传参、如何编写单元测试。

#### Spring Boot中使用Swagger2构建强大的RESTful API文档
由于Spring Boot能够快速开发、便捷部署等特性，相信有很大一部分Spring Boot的用户会用来构建RESTful API。而我们构建RESTful API的目的通常都是由于多终端的原因，这些终端会共用很多底层业务逻辑，因此我们会抽象出这样一层来同时服务于多个移动端或者Web前端。

这样一来，我们的RESTful API就有可能要面对多个开发人员或多个开发团队：IOS开发、Android开发或是Web开发等。为了减少与其他团队平时开发期间的频繁沟通成本，传统做法我们会创建一份RESTful API文档来记录所有接口细节，然而这样的做法有以下几个问题：

由于接口众多，并且细节复杂（需要考虑不同的HTTP请求类型、HTTP头部信息、HTTP请求内容等），高质量地创建这份文档本身就是件非常吃力的事，下游的抱怨声不绝于耳。
随着时间推移，不断修改接口实现的时候都必须同步修改接口文档，而文档与代码又处于两个不同的媒介，除非有严格的管理机制，不然很容易导致不一致现象。
为了解决上面这样的问题，本文将介绍RESTful API的重磅好伙伴Swagger2，它可以轻松的整合到Spring Boot中，并与Spring MVC程序配合组织出强大RESTful API文档。它既可以减少我们创建文档的工作量，同时说明内容又整合入实现代码中，让维护文档和修改代码整合为一体，可以让我们在修改代码逻辑的同时方便的修改文档说明。另外Swagger2也提供了强大的页面测试功能来调试每个RESTful API。

#### Spring Boot中Web应用的统一异常处理
我们在做Web应用的时候，请求处理过程中发生错误是非常常见的情况。Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。

选择一个之前实现过的Web应用（Chapter3-1-2）为基础，启动该应用，访问一个不存在的URL，或是修改处理内容，直接抛出异常，

统一异常处理
虽然，Spring Boot中实现了默认的error映射，但是在实际应用中，上面你的错误页面对用户来说并不够友好，我们通常需要去实现我们自己的异常提示。

下面我们以之前的Web应用例子为基础（Chapter3-1-2），进行统一异常处理的改造。

创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。@ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中

返回JSON格式
在上述例子中，通过@ControllerAdvice统一定义不同Exception映射到不同错误处理页面。而当我们要实现RESTful API时，返回的错误是JSON格式的数据，而不是HTML页面，这时候我们也能轻松支持。

本质上，只需在@ExceptionHandler之后加入@ResponseBody，就能让处理函数return的内容转换为JSON格式。

下面以一个具体示例来实现返回JSON格式的异常处理。

创建统一的JSON返回对象，code：消息类型，message：消息内容，url：请求的url，data：请求返回的数据

#### Spring Boot中使用Spring Security进行安全控制
原创 2016-05-28  翟永超  Spring Boot 被围观 110473 次
号外： 最近整理了之前编写的一系列内容做成了PDF，关注我的公众号"程序猿DD"来领取吧！
我们在编写Web应用时，经常需要对页面做一些安全控制，比如：对于没有访问权限的用户需要转到登录表单页面。要实现访问控制的方法多种多样，可以通过Aop、拦截器实现，也可以通过框架实现（如：Apache Shiro、Spring Security）。

本文将具体介绍在Spring Boot中如何使用Spring Security进行安全控制。

Spring Security配置
创建Spring Security的配置类WebSecurityConfig，具体如下：
通过@EnableWebSecurity注解开启Spring Security的功能
继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
configure(HttpSecurity http)方法
通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了/和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证。
通过formLogin()定义当需要用户登录时候，转到的登录页面。
configureGlobal(AuthenticationManagerBuilder auth)方法，在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。

可以看到，实现了一个简单的通过用户名和密码提交到/login的登录方式。

根据配置，Spring Security提供了一个过滤器来拦截请求并验证用户身份。如果用户身份认证失败，页面就重定向到/login?error，并且页面中会展现相应的错误信息。若用户想要注销登录，可以通过访问/login?logout请求，在完成注销之后，页面展现相应的成功消息。

到这里，我们启用应用，并访问http://localhost:8080/，可以正常访问。但是访问http://localhost:8080/hello的时候被重定向到了http://localhost:8080/login页面，因为没有登录，用户没有访问权限，通过输入用户名user和密码password进行登录后，跳转到了Hello World页面，再也通过访问http://localhost:8080/login?logout，就可以完成注销操作。

为了让整个过程更完成，我们可以修改hello.html，让它输出一些内容，并提供“注销”的链接。

#### Spring Boot中使用JdbcTemplate访问数据库
之前介绍了很多Web层的例子，包括构建RESTful API、使用Thymeleaf模板引擎渲染Web视图，但是这些内容还不足以构建一个动态的应用。通常我们做App也好，做Web应用也好，都需要内容，而内容通常存储于各种类型的数据库，服务端在接收到访问请求之后需要访问数据库获取并处理成展现给用户使用的数据形式。

本文介绍在Spring Boot基础下配置数据源和通过JdbcTemplate编写数据访问的示例。

数据源配置
在我们访问数据库的时候，需要先配置一个数据源，下面分别介绍一下几种不同的数据库配置方式。

首先，为了连接数据库需要引入jdbc支持，在pom.xml中引入如下配置：

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
嵌入式数据库支持
嵌入式数据库通常用于开发和测试环境，不推荐用于生产环境。Spring Boot提供自动配置的嵌入式数据库有H2、HSQL、Derby，你不需要提供任何连接配置就能使用。

比如，我们可以在pom.xml中引入如下配置使用HSQL

<dependency>
    <groupId>org.hsqldb</groupId>
    <artifactId>hsqldb</artifactId>
    <scope>runtime</scope>
</dependency>
连接生产数据源
以MySQL数据库为例，先引入MySQL连接的依赖包，在pom.xml中加入：

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.21</version>
</dependency>
在src/main/resources/application.properties中配置数据源信息

spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=dbuser
spring.datasource.password=dbpass
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
连接JNDI数据源
当你将应用部署于应用服务器上的时候想让数据源由应用服务器管理，那么可以使用如下配置方式引入JNDI数据源。

spring.datasource.jndi-name=java:jboss/datasources/customers
使用JdbcTemplate操作数据库
Spring的JdbcTemplate是自动配置的，你可以直接使用@Autowired来注入到你自己的bean中来使用。

举例：我们在创建User表，包含属性name、age，下面来编写数据访问对象和单元测试用例。

定义包含有插入、删除、查询的抽象接口UserService
public interface UserService {

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}
通过JdbcTemplate实现UserService中定义的数据访问操作
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from USER where NAME = ?", name);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USER");
    }
}
创建对UserService的单元测试用例，通过创建、删除和查询来验证数据库操作的正确性。

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserService userSerivce;

	@Before
	public void setUp() {
		// 准备，清空user表
		userSerivce.deleteAllUsers();
	}

	@Test
	public void test() throws Exception {
		// 插入5个用户
		userSerivce.create("a", 1);
		userSerivce.create("b", 2);
		userSerivce.create("c", 3);
		userSerivce.create("d", 4);
		userSerivce.create("e", 5);

		// 查数据库，应该有5个用户
		Assert.assertEquals(5, userSerivce.getAllUsers().intValue());

		// 删除两个用户
		userSerivce.deleteByName("a");
		userSerivce.deleteByName("e");

		// 查数据库，应该有5个用户
		Assert.assertEquals(3, userSerivce.getAllUsers().intValue());

	}

}
上面介绍的JdbcTemplate只是最基本的几个操作，更多其他数据访问操作的使用请参考：JdbcTemplate API

通过上面这个简单的例子，我们可以看到在Spring Boot下访问数据库的配置依然秉承了框架的初衷：简单。我们只需要在pom.xml中加入数据库依赖，再到application.properties中配置连接信息，不需要像Spring应用中创建JdbcTemplate的Bean，就可以直接在自己的对象中注入使用。

#### Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅
在上一篇Spring中使用JdbcTemplate访问数据库 中介绍了一种基本的数据访问方式，结合构建RESTful API和使用Thymeleaf模板引擎渲染Web视图的内容就已经可以完成App服务端和Web站点的开发任务了。

然而，在实际开发过程中，对数据库的操作无非就“增删改查”。就最为普遍的单表操作而言，除了表和字段不同外，语句都是类似的，开发人员需要写大量类似而枯燥的语句来完成业务逻辑。

为了解决这些大量枯燥的数据操作语句，我们第一个想到的是使用ORM框架，比如：Hibernate。通过整合Hibernate之后，我们以操作Java实体的方式最终将数据改变映射到数据库表中。

为了解决抽象各个Java实体基本的“增删改查”操作，我们通常会以泛型的方式封装一个模板Dao来进行抽象简化，但是这样依然不是很方便，我们需要针对每个实体编写一个继承自泛型模板Dao的接口，再编写该接口的实现。虽然一些基础的数据访问已经可以得到很好的复用，但是在代码结构上针对每个实体都会有一堆Dao的接口和实现。

由于模板Dao的实现，使得这些具体实体的Dao层已经变的非常“薄”，有一些具体实体的Dao实现可能完全就是对模板Dao的简单代理，并且往往这样的实现类可能会出现在很多实体上。Spring-data-jpa的出现正可以让这样一个已经很“薄”的数据访问层变成只是一层接口的编写方式。比如，下面的例子：


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
我们只需要通过编写一个继承自JpaRepository的接口就能完成数据访问，下面以一个具体实例来体验Spring-data-jpa给我们带来的强大功能。

使用示例
由于Spring-data-jpa依赖于Hibernate。如果您对Hibernate有一定了解，下面内容可以毫不费力的看懂并上手使用Spring-data-jpa。如果您还是Hibernate新手，您可以先按如下方式入门，再建议回头学习一下Hibernate以帮助这部分的理解和进一步使用。

工程配置
在pom.xml中添加相关依赖，加入以下内容：


<dependency
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
在application.xml中配置：数据库连接信息（如使用嵌入式数据库则不需要）、自动创建表结构的设置，例如使用mysql的情况如下：

spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
spring.jpa.properties.hibernate.hbm2ddl.auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：

create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
至此已经完成基础配置，如果您有在Spring下整合使用过它的话，相信你已经感受到Spring Boot的便利之处：JPA的传统配置在persistence.xml文件中，但是这里我们不需要。当然，最好在构建项目时候按照之前提过的最佳实践的工程结构来组织，这样以确保各种配置都能被框架扫描到。

创建实体
创建一个User实体，包含id（主键）、name（姓名）、age（年龄）属性，通过ORM框架其会被映射到数据库表中，由于配置了hibernate.hbm2ddl.auto，在应用启动的时候框架会自动去数据库中创建对应的表。

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    // 省略构造函数

    // 省略getter和setter

}
创建数据访问接口
下面针对User实体创建对应的Repository接口实现对该实体的数据访问，如下代码：

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
在Spring-data-jpa中，只需要编写类似上面这样的接口就可实现数据访问。不再像我们以往编写了接口时候还需要自己编写接口实现类，直接减少了我们的文件清单。

下面对上面的UserRepository做一些解释，该接口继承自JpaRepository，通过查看JpaRepository接口的API文档，可以看到该接口本身已经实现了创建（save）、更新（save）、删除（delete）、查询（findAll、findOne）等基本操作的函数，因此对于这些基础操作的数据访问就不需要开发者再自己定义。

在我们实际开发中，JpaRepository接口定义的接口往往还不够或者性能不够优化，我们需要进一步实现更复杂一些的查询或操作。由于本文重点在spring boot中整合spring-data-jpa，在这里先抛砖引玉简单介绍一下spring-data-jpa中让我们兴奋的功能，后续再单独开篇讲一下spring-data-jpa中的常见使用。

在上例中，我们可以看到下面两个函数：

User findByName(String name)
User findByNameAndAge(String name, Integer age)
它们分别实现了按name查询User实体和按name和age查询User实体，可以看到我们这里没有任何类SQL语句就完成了两个条件查询方法。这就是Spring-data-jpa的一大特性：通过解析方法名创建查询。

除了通过解析方法名来创建查询外，它也提供通过使用@Query 注解来创建查询，您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数，就像例子中的第三个findUser函数一样。

Spring-data-jpa的能力远不止本文提到的这些，由于本文主要以整合介绍为主，对于Spring-data-jpa的使用只是介绍了常见的使用方式。诸如@Modifying操作、分页排序、原生SQL支持以及与Spring MVC的结合使用等等内容就不在本文中详细展开，这里先挖个坑，后续再补文章填坑，如您对这些感兴趣可以关注我博客或简书，同样欢迎大家留言交流想法。

单元测试
在完成了上面的数据访问接口之后，按照惯例就是编写对应的单元测试来验证编写的内容是否正确。这里就不多做介绍，主要通过数据操作和查询来反复验证操作的正确性。

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {

        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, userRepository.findAll().size());

        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

        // 测试删除姓名为AAA的User
        userRepository.delete(userRepository.findByName("AAA"));

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, userRepository.findAll().size());

    }
}
参考资料
hibernate.hbm2ddl.auto配置详解



