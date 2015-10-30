Yet another pool - a generic pool implementation for Java.

For details, please read the main Yapool package description in the [apidocs](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/index.html).

For examples of what makes Yapool tick, have a look at the following classes:
  * [ExampleEvents](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleEvents.java) - creating a simple pruned pool and listening to pool events.
  * [ExampleConfig](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleConfig.java) - using the BeanConfig class to configure a pool and pool factory, and log all properties.
  * [ExampleHsql](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleHsql.java) - create a simple HSQL database connection pool suitable for unit testing.
  * [ExampleHib4Jpa](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleHib4Jpa.java) - use Hibernate 4 via JPA and configure the SqlPool via "persistence.xml" in a Java SE environment.
  * [ExampleValidateOnCheckIn](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleValidateOnCheckIn.java) - enable validation on check in (when a resource is released). This is not a configurable option in Yapool (unlike other database pools), but this example shows how (easily) it can be done.
  * [ExampleQueryCache](https://code.google.com/p/yapool/source/browse/trunk/yapool/yapool-examples/src/main/java/nl/fw/yapool/examples/ExampleQueryCache.java) - advanced example showing how to load queries from file and use a query cache for maximum efficiency.

Downloads are available in the [source downloads](https://code.google.com/p/yapool/source/browse/downloads) directory (use the "raw file link" at the rigth side for each file).

Or use the following links:
  * [0.9.3 binaries](https://yapool.googlecode.com/svn/downloads/yapool-0.9.3-artifacts.zip) (includes Javadoc)
  * [0.9.3 project](https://yapool.googlecode.com/svn/downloads/yapool-0.9.3-project.zip) (includes all source code).