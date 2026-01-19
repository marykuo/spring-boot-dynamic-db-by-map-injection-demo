# spring-boot-dynamic-db-by-map-injection-demo

a solution for multi-tenant operation

## 集合類型的自動裝配 (Map Injection)

這是這段程式碼最精妙的地方。

透過 `Map<String, DataSource>` 實現策略模式的簡化版。

* 在 `JdbcTemplateProvider` 的建構子中：
```java
public JdbcTemplateProvider(Map<String, DataSource> dataSourceMap)

```


* **Spring 功能**: 當 Spring 發現建構子參數是一個 `Map<String, BeanType>` 時，它會自動將所有類型為 `DataSource` 的 Bean 收集起來。
* **Key/Value 映射**: Map 的 **Key** 會是 Bean 的名稱（即 `"PROD"` 和 `"TEST"`），**Value** 則是對應的實例。這讓你不需要寫死 `if-else` 就能動態獲取不同的資料源。
