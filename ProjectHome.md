Project moved to [Github](https://github.com/fwi/yapool)

Yet Another Pool provides a [generic](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/package-summary.html#package_description) pool implementation (using Java concurrent classes) with special attention to database connection pools. A default [SqlPool](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/sql/SqlPool.html) is efficient, configured to prevent leasing invalid connections to the application and closes idle connections.

Yet, any pool can be customized (during runtime) by adding and removing [listeners](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/listener/PoolListener.html) that can react to pool events as they occur (e.g. a listener can measure pool [performance](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/listener/PoolPerformance.html)).

Support for [datasources](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/sql/datasource/DataSourceProxy.html), [Hibernate](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/sql/hibernate/package-summary.html) and [Tomcat](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/sql/datasource/DataSourceFactory.html) is included. There are also a couple of [bean](https://yapool.googlecode.com/svn/branches/yapool/apidocs/0.9/nl/fw/yapool/BeanConfig.html)-utilities available for setting values in the many get/set-bean methods in the pool (related) classes.

Examples, downloads and resources are available at the main [Wiki](https://code.google.com/p/yapool/wiki/YapoolMain) page.

Release 0.9.1 (and higher) is suitable for production (but, as always, verify with acceptance tests). Latest stable release is 0.9.3.