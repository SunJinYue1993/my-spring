## @Component/@ComponentScan/@Configuration注册和增强过程
### 1. 概括

```markdown
## 核心点
- xml配置或annotation配置封装BeanDefinition是两个流程,需要分开说
## 如何将组件加入IOC?
### 1. 在类上加@Component注解@Controller注解等等注解,这是怎样一个流程?
	首先需要register(xxx.class)进行注册,那么就会生成这个xxx.class的BeanDefinition了,需要注意的是这个方法是AnnotationConfigApplicationContext对象.
### 2. 那有一千了类,岂不是要register(xxx.class)1000次?
	其实不然,当一个类转化成BeanDefinition时,我们可以对BeanDefinition进行增强,这是一个Bean需要经历的生命周期,解析这个xxx类时,解析到他由@ComponentScan修饰时,会自动扫描,把扫描到的类也生成BeanDefinition;有个ConfigurationClassPostProcessor类就干这个是,具体逻辑在ComponentScanAnnotationParser类中!
	所以,被扫描到的类BeanDefinition生成在invokeBeanFactoryPostProcessors(beanFactory);这个增强期
### 3. spring自带的BeanDefinition?
	当加了@Component等注解修饰的类生成BeanDefinition时,spring会自动生成一些自带的BeanDefinition来对这个类进行处理,详情转day13.
beanFactory类的beanDefinitionMap属性
RootBeanDefinition:
1. org.springframework.context.annotation.internalConfigurationAnnotationProcessor
2. org.springframework.context.event.internalEventListenerFactory
3. org.springframework.context.event.internalEventListenerProcessor
4. org.springframework.context.annotation.internalAutowiredAnnotationProcessor
```

### 2. 注册

```java
// 1.new 一个注解BeanDefinition的读取器
new AnnotatedBeanDefinitionReader(this);
// 2.AnnotationConfigUtils注册一些处理注解(比如:@Autowired，@Value...)的beanFactoryPostProcessor和beanPostProcessor
AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
// 3.注册我们配置的类(比如@Configuration修饰的类)
AnnotationConfigApplicationContext().register(xx.class);
BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, this.registry);
```

### 3. 增强

#### 1. AbstractApplicationContext
```java
	// 调用上下文中 注册为bean的工厂处理器(例如: MyBeanFactoryPostProcessor, ConfigurationClassPostProcessor)
	invokeBeanFactoryPostProcessors(beanFactory);  ->跳入
```
#### 2. PostProcessorRegistrationDelegate
代理所有的BeanDefinitionRegistry
```java
	// 框架需要优先处理的BeanDefinition(bean定义)
	String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
	
	// 当执行完这个方法，扫描(ComponentScan)的类加入BeanDefinitionMap中
	// 参数：1.配置类处理器ConfigurationClassPostProcessor 2.spring默认工厂DefaultListableBeanFactory 3.spring应用启动标识
	invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry, beanFactory.getApplicationStartup());  ->跳入
	
	...

```
#### 3. ConfigurationClassPostProcessor
处理我们配置类，也就是加了@Configuration注解的处理类
 * 此类是一个后置处理器的类，主要功能是参与BeanFactory的建造，主要功能如下
 *   1、解析加了@Configuration的配置类
 *   2、解析@ComponentScan扫描的包
 *   3、解析@ComponentScans扫描的包
 *   4、解析@Import注解
```java
	// 根据BeanDefinitionRegistry构建并验证配置模型
	public void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {
		// 获取所有BeanDefinition,包括spring自带的{
							internalConfigurationAnnotationProcessor;internalEventListenerFactory
							internalEventListenerProcessor;internalAutowiredAnnotationProcessor}和加@Configuration注解的
		String[] candidateNames = registry.getBeanDefinitionNames();
		if() {
			log.debug(这几个是spring自带的Bean definition)
		} else {
			candidates.add(Bean definition) //将不是Spring自带的BeanDefinition加入这个list, 下面的parse方法将构建这个配置
		}
		// 构建 参数：BeanDefinitionHolder{BeanDefinition beanDefinition，String beanName，String[] aliases}
		parser.parse(candidates);
		// 校验
		parser.validate();
	
	}
```

#### 4. ConfigurationClassParser
专门处理配置类的核心类
```java
	// 递归处理所有的配置类; 参数: 1.配置类configClass 2.将所有配置类统一成wrapper：sourceClass 3.拦截器
	do {
		sourceClass = doProcessConfigurationClass(configClass, sourceClass, filter);
	}
	while (sourceClass != null);
	// 放入map中
	this.configurationClasses.put(configClass, configClass);
```
最核心的来了！！
```java
	protected final SourceClass doProcessConfigurationClass(ConfigurationClass configClass, SourceClass sourceClass, Predicate<String> filter) {
		// TODO：Process any @ComponentScan annotations
		// 配置类用@ComponentScan ->注释，立即执行扫描 -> 调到
		Set<BeanDefinitionHolder> scannedBeanDefinitions = this.componentScanParser.parse(componentScan, sourceClass.getMetadat().getClassName()); 
		// TODO：Process any @Import annotations
		// TODO：Process any @ImportResource annotations
		// TODO：Process individual @Bean methods
	}
```
#### 5. ComponentScanAnnotationParser
解析@ComponentScan注解的值
```java
	// 注册中心,向注册中心新增一个bean定义
	ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this.registry, componentScan.getBoolean("useDefaultFilters"), this.environment, this.resourceLoader);
	scanner.doScan(StringUtils.toStringArray(basePackages));
```
#### 6. ClassPathBeanDefinitionScanner
```java
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		// TODO:注册bean定义
		registerBeanDefinition(definitionHolder, this.registry);
	}
```

此时扫描到的BeanDefinition已经构建了！
总结: 

@Component要加入容器的方式:
1. ac.register(@Component修饰的类);直接注册生成AnnotatedGenericBeanDefinition
2. ac.register(@ComponentScan修饰的类);由ConfigurationClassParser处理
3. ac.register(@Configuration修饰的类);在这个类上+@ComponentScan也会被ConfigurationClassParser解析到
ConfigurationClassParser主要工作有以下几点:
	// TODO：Process any @ComponentScan annotations
	// TODO：Process any @Import annotations
    // TODO：Process any @ImportResource annotations
	// TODO：Process individual @Bean methods
