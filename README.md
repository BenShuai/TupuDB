### sun-parent:

包版本管理【基础引用】

### sun-common：

框架辅助类和工具类等封装，以及一些基础依赖

### sun-bean：

业务实体类，service类，mybates的mapper类等

### sun-web：

controller类，任务类，配置类等




### 配置文件：

配置文件的入口在：`\trunk\sun-web\src\main\resources\application.properties`

直接修改里面的`spring.profiles.active`的值来配置使用的是哪一个配置文件,比如值为`dev`的时候:使用的就是：`application-dev.properties`文件的配置。

`logback-spring.xml`是日志处理和输出相关的配置。


数据的存储路径配置在`\trunk\sun-common\src\main\resources\base.properties`文件中