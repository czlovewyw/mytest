运行说明：
    1.修改src/main/resources/dataSource.properties文件和src/pom.xml中<password>110</password>的数据库密码
    2.执行数据库脚本, mvn sql:execute
    3.运行程序: mvn clean jetty:run
    (注意：jetty和sql是groupId:artifactId:version的简写，需要在setting.xml将jetty和sql加入到plginGroups中，
        <pluginGroups>
            <pluginGroup>org.mortbay.jetty</pluginGroup>
            <pluginGroup>org.codehaus.mojo</pluginGroup>
        </pluginGroups>
    )
    或者，直接打包，放到Servlet容器中运行。打包命令: mvn clean package
在Search Hotels页面IE 6 有一个bug,当鼠标移到文本框上时padding-bottom会加倍。

install and run description:
    1.modify MySQL database password in src/main/resources/dataSource.properties file and src/pom.xml file
    2.execute the db scripts, mvn sql:execute
    3.run the program: mvn clean jetty:run , open the broswer and input the url: http://localhost:8080/SprnigWebFlowTutorial
    (note: you need to put the jetty in maven groups )
    or you can package it and copy the artifact to the servlet container and runs it.
