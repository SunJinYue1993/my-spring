## bean的生命周期  ->  BeanFactoryPostProcessor阶段
#### BeanFactoryPostProcessor接口在bean的生命周期中的作用？
1. 正常情况下OneService(java bean) -> OneService(spring bean)走蓝色的线，但是MyBeanFactoryPostProcessor对这个过程进行了扩展。
   此时OneService(java bean) -> OneService(spring bean) 走的红色的线;  