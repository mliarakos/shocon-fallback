### Issue

Multiple configs chained together as fallbacks behave as expected:

```scala
val a: Config = ...
val b: Config = ...
val c: Config = ...

c.withFallback(b).withFallback(a)
```

Configs tiered as fallbacks do not:

```scala
val a: Config = ...
val b: Config = ...
val c: Config = ...

c.withFallback(b.withFallback(a))
```

Settings in the `a` config are not found.

### Reproduce the Issue

1. Clone the issue repo:

   ```sh
   git clone git@github.com:mliarakos/shocon-fallback.git
   ```
1. Run the test:

   ```sh
   cd shocon-fallback
   sbt test
   ```
1. The first test should succeed while the second test should fail to demonstrate the issue.
