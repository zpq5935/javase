``Sink`` 模块包含 底层框架逻辑及部分抽象实现（不对外开放，均为包名访问权限）, 包含下列对象

1. ``Node``接口及其部分抽象实现
2. ``Sink``接口及其部分抽象实现
3. ``StreamShape``、``StreamOpFlag``、``PipelineHelper``

## Node
1. 代表n的泛型T的元素
2. 不直接存储元素

## Sink

## StreamShape

## StreamOpFlag
与流、操作的特征绑定，控制、specialize、优化 计算

## PipelineHelper