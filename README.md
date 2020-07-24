Pull 3577
=========

A benchmark I created for [a discussion at a pull request for kotlin](https://github.com/JetBrains/kotlin/pull/3577).

Try it yourself by cloning this repository and running `./gradlew benchmark`.

Example Output:
```
main: me.enterman.pull3577.Benchmark.endsWithAny

Warm-up 1: 3674524.926 ops/s
...
Iteration 5: 3666832.333 ops/s

3631132.310 ±(99.9%) 81319.436 ops/s [Average]
  (min, avg, max) = (3611841.678, 3631132.310, 3666832.333), stdev = 21118.399
  CI (99.9%): [3549812.873, 3712451.746] (assumes normal distribution)


main: me.enterman.pull3577.Benchmark.substringAfterLastHashSet

Warm-up 1: 52667827.459 ops/s
...
Iteration 5: 49419721.836 ops/s

49924359.329 ±(99.9%) 1729069.547 ops/s [Average]
  (min, avg, max) = (49419721.836, 49924359.329, 50331538.224), stdev = 449033.871
  CI (99.9%): [48195289.782, 51653428.876] (assumes normal distribution)
```