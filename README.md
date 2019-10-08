# VixCourts
Assignment for the 2019 edition of the "Web Development and the Semantic Web" course, by Gabriel Nogueira and Silas Campos

### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse 2019-06](http://www.eclipse.org/);

2. Install [WildFly 17.x](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `pooptime` and a user called `dwws` with password `dwws` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
 <datasource jta="true" jndi-name="java:/jboss/datasources/VixCourts" pool-name="VixCourtsPool" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost:3306/vixcourts</connection-url>
	<driver>mysql</driver>
	<security>
	    <user-name>dwws</user-name>
	    <password>dwws</password>
	</security>
 </datasource>
```

6. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

7. You might have to adjust the server settings in the imported project: right-click the _PoopTime_ project and select _Properties_. In the _Server_ section, select the _WildFly 13.x_ server. In the _Targeted Runtimes_ section, select the _WildFly 13.x Runtime_;

## Authors

* **Gabriel Nogueira**
* **Silas Campos**
