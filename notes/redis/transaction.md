## Transaction

redisTemplate 을 사용해 redis 를 컨트롤 하는 경우, 아래의 설정을 통해 트랜잭션을 수행할 수 있다
```kotlin
template.setEnableTransactionSupport(true)
```

이렇게 설정을 해주면 @Transactional 어노테이션을 이용할 수 있다
```kotlin
@Transactional
fun removeAll() {
    // ...
}
```

redis 의 트랜잭션은 multi, 커맨드를 이용해 이루어진다  
```shell
MULTI
DEL "07b832ef-9dcd-4486-92cf-c9550f9e7404"
DEL "d264a5af-9928-4681-a027-2ebfc0ddf3c1"
EXEC
``` 
위에서 MULTI 와 EXEC 사이의 커맨드들은 원자적으로 실행된다.
만약 EXEC 커맨드가 아니라 DISCARD 커맨드를 실행하면 MULTI 이후의 커맨드는 실행되지 않는다

```shell
MULTI
DEL "07b832ef-9dcd-4486-92cf-c9550f9e7404"
DISCARD
```

주의할 점은 multi-exec 는 rollback 을 지원하지 않는다는 것이다  
MULTI-EXEC 사이의 커맨드를 실행하다가 에러가 나도 나머지 커맨드들은 그대로 실행된다

또한 코드로 실험해봤을 때 @Transactional(propagation = REQUIRES_NEW) 로 새로운 트랜잭션 생성을 요청해도
MULTI-EXEC 구문은 하나만 실행된다. 아마 지원하지 않는 듯...?

redis 에서 @Transactional 어노테이션은 제한적으로 지원할 뿐이니 주의해서 사용하도록 하자
