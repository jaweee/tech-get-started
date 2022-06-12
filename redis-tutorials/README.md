# Redis


## 类型

1. String：二进制安全的字符串
2. Lists：字符串列表，根据插入顺序的linked list
3. Sets：字符串集合，无序唯一
4. Sorted Sets：字符串集合，但是每个元素都有一个浮点数（称之为score），通过这个score排序
5. Hashes：键值对key-value均有字符串组成Map<String, String>
6. Bit Array：通过特殊命令，把字符串当做bit来操作
7. HyperLogLogs：用来评估集合的基数
8. Streams：


### 键

1. 不要太长，comparison太花时间
2. 不要太短，不利于阅读
3. find balance


### String类型

1. set命令模板
```shell
set key value [expiration ex seconds | px milliseconds] [nx | xx]
```

2. get命令模板

```shell
get key
```

#### 1. 基本命令

```bash
set mykey somevalue

get mykey
```

#### 2. 修改值

```shell
set mykey 100

incr mykey

decr myker

incrby mykey 3

decrby mykey 4

get mykey
```