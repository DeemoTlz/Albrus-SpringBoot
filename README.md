# Albrus-Spring Boot
(:3[▓▓▓▓▓▓▓▓]
> Systematically study Spring Boot and practice and record to deepen understanding.
>
> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

## 时代背景

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

==**Spring 5 重大升级之响应式编程！！！**==![image.png](images/1602642309979-eac6fe50-dc84-49cc-8ab9-e45b13b90121.png)

还得益于 JDK 8，优化源码架构设计。

**Spring Boot 优点：**

- 创建独立 Spring 应用
- 内嵌 web 服务器
- starter 组件式依赖，简化组件包引入程序，简化版本依赖
- 自动配置 Spring 和第三方功能
- 提供生产级别的监控、健康检查及外部化配置
- 无代码生成，无需编写 XML

**时代背景：**

**微服务：**

微服务概念于 2014 年被[完整](https://martinfowler.com/microservices/)提出：

- 微服务是一种架构风格
- 一个应用拆分为一组小型服务
- 每个服务运行在自己的进程内，也就是可独立部署和升级
- 服务之间使用轻量级HTTP交互
- 服务围绕业务功能拆分
- 可以由全自动部署机制独立部署
- 去中心化，服务自治。服务可以使用不同的语言、不同的存储技术

**分布式：**

- 远程调用

- 服务发现

- 负载均衡

- 服务容错

- 配置管理

- 服务监控

- 链路追踪

  一个复杂的业务流程可能需要连续调用多个微服务，我们需要记录一个完整业务逻辑涉及的每一个微服务的运行状态，再通过可视化**链路图**展现，帮助软件工程师在系统出错时分析解决问题，常见的解决方案有 Zipkin，SkyWalking。

- 日志管理

  微服务架构默认将应用日志分散保存在每一个微服务节点上，当系统进行用户行为分析、数据统计时必须收集所有节点日志数据，非常不方便。这时候我们需要一个独立的日志平台，**收集**所有节点的日志数据并可方便对其进行汇总**分析**，然后进行可视化**展示**，常见的解决方案有ELK（Elasticsearch + Logstash + Kibana），EFK（Elasticsearch + Fluentd + Kibana）。

- 任务调度

- ......

**云原生：**

- 服务自愈
- 弹性伸缩
- 服务隔离
- 自动化部署
- 灰度开发
- 流量治理
- ......

## 依赖管理

> 自动管理依赖版本。

### parent

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.7</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

能够更方便地升级依赖组件的版本，设置 `properties` 即可：

```xml
spring-boot-dependencies-2.6.7.pom
<properties>
    <activemq.version>5.16.4</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    <appengine-sdk.version>1.9.96</appengine-sdk.version>
    <artemis.version>2.19.1</artemis.version>
    <aspectj.version>1.9.7</aspectj.version>
    ...
</properties>
```

### dependencies

```xml
<properties>
    <spring-boot.version>2.6.7</spring-boot.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

升级依赖组件的版本需要写全 GAV 坐标。

## 自动配置

- 自动配置 Tomcat

- 自动配置 Spring MVC

- 自动配置 Web 常见功能，如：字符编码

- 默认包扫描

  主程序所在包及其子包

- 各种配置拥有默认值

  可以通过修改 `application.properties/application.yaml` 配置达到快速修改默认值的目的

- 按需加载

- 。。。

## 底层注解

### `@Configuration`

- 是一个配置类 > 配置文件，使用 `@Bean` 给容器注册组件

- 配置类本身也是组件：`XxxConfiguration$$EnhancerBySpringCGLIB$$3f51e91b@22d6cac2`

- 在 Spring Boot 2 (Spring 5.2) 之后引入了一个属性：`boolean proxyBeanMethods() default true;`

  ```java
  public @interface Configuration {
      boolean proxyBeanMethods() default true;
  }
  ```

  - Full：为 `true` 时，`XxxConfiguration` 注册的是一个代理对象：**`XxxConfiguration$$EnhancerBySpringCGLIB`**

    通过 `XxxConfiguration` 对象调用里面的 `@Bean` 方法（单例时）返回的都是同一个对象，**会保持单例模式**

  - Lite：为 `false` 时，每次调用都会重新创建一个对象

  `Full(proxyBeanMethods = true)` 下，可以解决组件依赖问题：

  ```java
  @Configuration
  public class AlbrusConfiguration {
      @Bean
      public User user() {
          User user = new User();
          // 组件依赖
          user.setPet(pet());
          return user;
      }
      
      @Bean
      public Pet pet() {
          return new Pet();
      }
  }
  ```

  **如何选择 Full || Lite ？**如果仅仅是添加简单的 Bean 组件（没有组件依赖、不会手动调用 `XxxConfiguration` 对象的 `@Bean` 方法），可以修改为 `false` 增加启动速度。

### `@Bean、@Component、@Controller、@Service、@Repository`

常用注解。

### `@ComponentScan、@Import`

`@Import` 注解注册的组件，默认名称为**全类名**。

### `@Conditional`

条件装配：

![image.png](images/1602835786727-28b6f936-62f5-4fd6-a6c5-ae690bd1e31d.png)

### `@ImportResource`

导入原生配置文件引用：`@ImportResource("classpath:beans.xml")`。

### `@ConfigurationProperties`

> 只有容器中的类可以使用 `@ConfigurationProperties` 注解。

1. 为容器中的组件加注解：`@ConfigurationProperties(prefix = "albrus")`

2. 在配置类上 `@EnableConfigurationProperties(Cat.class)` + `@ConfigurationProperties(prefix = "albrus")`

   可以用于为导入的第三方包组件设置初始值。

## 自动配置原理

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {...}
```

### `@SpringBootConfiguration`

```java
@Configuration
@Indexed
public @interface SpringBootConfiguration {

	/**
	 * @return whether to proxy {@code @Bean} methods
	 * @since 2.2
	 */
	@AliasFor(annotation = Configuration.class)
	boolean proxyBeanMethods() default true;

}
```

是一个 `@Configuration` 配置类。

### `@ComponentScan`

指定包扫描路径。

### `@EnableAutoConfiguration`

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {...}
```

1. `@AutoConfigurationPackage`

   ```java
   @Import(AutoConfigurationPackages.Registrar.class)
   public @interface AutoConfigurationPackage {...}
   ```

   给容器导入一个 `AutoConfigurationPackages.Registrar` 组件：

   ```java
   // org.springframework.boot.autoconfigure.AutoConfigurationPackages#register
   registry.registerBeanDefinition(BEAN, new BasePackagesBeanDefinition(packageNames));
   ```

   `packageNames`：`@SpringBootApplication` 启动类的包路径，也就是==**配置默认的包扫描路径**==。

2. `@Import(AutoConfigurationImportSelector.class)`

   给容器导入一个 `AutoConfigurationImportSelector` 组件：

   ```java
   public class AutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware, ResourceLoaderAware, BeanFactoryAware, EnvironmentAware, Ordered {
       
       @Override
       public String[] selectImports(AnnotationMetadata annotationMetadata) {
           if (!isEnabled(annotationMetadata)) {
               return NO_IMPORTS;
           }
           // 为容器自动注入的组件
           AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(annotationMetadata);
           return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
       }
       
       /**
   	 * Return the auto-configuration class names that should be considered. By default
   	 * this method will load candidates using {@link SpringFactoriesLoader} with
   	 * {@link #getSpringFactoriesLoaderFactoryClass()}.
   	 * @param metadata the source metadata
   	 * @param attributes the {@link #getAttributes(AnnotationMetadata) annotation
   	 * attributes}
   	 * @return a list of candidate configurations
   	 */
   	protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
           // **重点**
   		List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
   				getBeanClassLoader());
   		Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
   				+ "are using a custom packaging, make sure that file is correct.");
   		return configurations;
   	}
   }
   ```

   **重点关注：**

   ```java
   protected Class<?> getSpringFactoriesLoaderFactoryClass() {
       return EnableAutoConfiguration.class;
   }
   
   public final class SpringFactoriesLoader {
   	/**
   	 * The location to look for factories.
   	 * <p>Can be present in multiple JAR files.
   	 */
   	public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
   }
   ```

   也就是说，==**从 `META-INF/spring.factories` 文件中，取出 `EnableAutoConfiguration.class` 对应的值：Class 集合**==。

   组件具体加载不加载，依据 Spring Boot 的按需加载机制决定。

### 修改默认配置

```java
// org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
@Bean
@ConditionalOnBean(MultipartResolver.class)
@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
public MultipartResolver multipartResolver(MultipartResolver resolver) {
    // Detect if the user has created a MultipartResolver but named it incorrectly
    return resolver;
}
```

组合拳：`@ConditionalOnBean` + `@ConditionalOnMissingBean`

### `SpringBootCondition`？

`Condition` 生效？

```java
// org.springframework.context.annotation.ConditionEvaluator#shouldSkip
/**
 * Determine if an item should be skipped based on {@code @Conditional} annotations.
 * @param metadata the meta data
 * @param phase the phase of the call
 * @return if the item should be skipped
 */
public boolean shouldSkip(@Nullable AnnotatedTypeMetadata metadata, @Nullable ConfigurationPhase phase) {
    if (metadata == null || !metadata.isAnnotated(Conditional.class.getName())) {
        return false;
    }

    if (phase == null) {
        if (metadata instanceof AnnotationMetadata &&
            ConfigurationClassUtils.isConfigurationCandidate((AnnotationMetadata) metadata)) {
            return shouldSkip(metadata, ConfigurationPhase.PARSE_CONFIGURATION);
        }
        return shouldSkip(metadata, ConfigurationPhase.REGISTER_BEAN);
    }

    // 获取所有
    List<Condition> conditions = new ArrayList<>();
    for (String[] conditionClasses : getConditionClasses(metadata)) {
        for (String conditionClass : conditionClasses) {
            Condition condition = getCondition(conditionClass, this.context.getClassLoader());
            conditions.add(condition);
        }
    }

    AnnotationAwareOrderComparator.sort(conditions);

    for (Condition condition : conditions) {
        ConfigurationPhase requiredPhase = null;
        if (condition instanceof ConfigurationCondition) {
            requiredPhase = ((ConfigurationCondition) condition).getConfigurationPhase();
        }
        if ((requiredPhase == null || requiredPhase == phase) && !condition.matches(this.context, metadata)) {
            return true;
        }
    }

    return false;
}
```





## 自定义 starter

> All **official** starters follow a similar naming pattern; `spring-boot-starter-*`
>
> For example, a third-party starter project called `thirdpartyproject` would typically be named `thirdpartyproject-spring-boot-starter`
>
> named: `project-spring-boot-starter`

