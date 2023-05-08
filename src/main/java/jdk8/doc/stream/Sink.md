``Sink`` 模块包含 底层框架逻辑及部分抽象实现（不对外开放，均为包名访问权限）, 包含下列对象

1. ``Node``接口及其部分抽象实现
2. ``Sink``接口及其部分抽象实现
3. ``StreamShape``、``StreamOpFlag``、``PipelineHelper``

## Node
1. 代表n的泛型T的元素
2. 不直接存储元素

## Sink
子接口 Sink.OfInit、Sink.OfLong、Sink.OfDouble

子抽象类 Sink.ChainedReference、Sink.ChainedInt、Sink.ChainedLong、Sink.ChainedDouble

## StreamShape

## StreamOpFlag
与流、操作的特征绑定，控制、specialize、优化 计算

书读百遍，其义自见（没啥好说的）
1. 如何记录
   1. 通过 combineOpFlag方法 理解
2. 如何修改（新增、移除特性）
   1. 通过 combineOpFlag方法 理解
3. 如何查询
   1. 通过 isKnown方法 等

```text
StreamOpFlag.getMask(int) : 
入参一般为操作（如 StreamOpFlag.IS_DISTINCT 意为设置为 具备去重OpFlag）
目前函数内部会将目前已有的所有操作位（StreamOpFlag的FLAG_STREAM_IS、FLAG_STREAM_NO的或运算的结果，默认每个OpFlag占2位）上含1的置为0，其他都置为1

combineOpFlag(int newStreamOrOpFlags, int prevCombOpFlags) : 
结合 getMask 理解，该函数生成一个 int（去除原特性值对 opFlag 上的性质，再附加上 newStreamOrOpFlags）
```

## PipelineHelper