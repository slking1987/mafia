# MAFIA

基于spring boot的后台API服务启动项目  
v0.5 Present by slking1987   
*感谢曾经一起工作的同事，如果有幸看到请联系我*

---

### 0. 代码结构
mafia-api API接口模块，用于定义接口及最基本的参数校验  
mafia-core 核心组件模块，spring组件，数据库，缓存，工具类等  
mafia-interface 调用外部服务模块，可自行扩展，http，web service，thrift等  
mafia-srv-* 业务模块，可自行扩展，编写具体的业务逻辑实现

### 1. 日志

建议使用LogService，方便日志统一格式化，以及后续日志转存迁移

### 2. 异常

建议继承MafiaException，结合RespCode，API层会有统一的异常返回处理(MafiaExpHandler)

### 3. API

目前为非标准REST接口，通过请求头中的method进行分发，如 method=demo.get，则相应方法为DemoApiService.get()

### 4. 安全

默认屏蔽http get访问方式，通过appId,allowMethodList,allowIpList,key等控制调用方的签名验证，请求方法验证，请求IP验证等，传输数据为Base64加密  
防爬、SQL注入、XSS攻击等暂未提供，需要自行实现

### 5. 测试

已集成JUnit测试，继承MafiaTest.java即可独立运行，建议service层都添加测试用例，api层也可通过HttpUtil模拟调用测试

### 6. 缓存

使用RedisUtil即可，可自行扩展

### 7. 配置

`mafia-core/src/main/resources`

### 8. 国际化

采用传统KV方式，国际化配置位于mafia-core/src/main/resources/i18n，系统中使用`I18nUtil`指定key即可获取msg

### 9. 约定

> * 枚举类型与数据库中保存的类型映射使用MyBatis转换，通过自定义的`com/vb/mafia/core/config/EnumTypeHandler.java`实现tinyInt到枚举类型的转换；
实现枚举类需要实现接口MafEnum，然后将枚举类添加到`mybatis-config.xml`中，实现id到枚举类型的转换
