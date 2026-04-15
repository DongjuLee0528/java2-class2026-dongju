# java2-class2026-dongju
# 202430219 이동주

---
## 7주차 정리 (4월 15일)

---

## 1. 클래스 (Class)

클래스는 객체를 만들기 위한 설계도이며, `class` 키워드로 선언한다. 필드(데이터 저장)와 메소드(기능 수행)로 구성된다.

`public`을 클래스에 붙이면 다른 모든 클래스에서 해당 클래스를 사용할 수 있고, 멤버에 붙이면 다른 모든 클래스에서 해당 멤버에 접근할 수 있다.

```java
public class Circle {
    int radius;
    String name;

    public double getArea() {
        return 3.14 * radius * radius;
    }
}
```

핵심 개념:
- 클래스로 객체를 생성 (`new`)
- 객체는 필드 값 저장 + 메소드 호출
- 같은 클래스로 여러 객체 생성 가능하며 서로 독립적

---

## 2. 객체 생성과 활용

객체는 선언 → 생성 → 사용 순서로 다룬다. `new` 키워드로 메모리를 할당하고, 점 연산자(`.`)로 필드와 메소드에 접근한다.

```java
Circle pizza;                  // 1. 선언
pizza = new Circle();          // 2. 생성
pizza.radius = 10;             // 3. 필드 값 설정
pizza.name = "자바피자";
double area = pizza.getArea(); // 4. 메소드 호출
```

객체를 생성하지 않고 접근하면 `NullPointerException`이 발생한다.

```java
// 잘못된 코드
Circle pizza;
pizza.radius = 10; // NullPointerException

// 올바른 코드
pizza = new Circle();
pizza.radius = 10;
```

---

## 3. 생성자 (Constructor)

객체 생성 시 자동으로 호출되어 필드를 초기화하는 메소드다.

특징:
- 클래스 이름과 동일해야 한다
- 리턴 타입이 없다 (`void`도 불가)
- `new` 연산자와 함께 딱 한 번 실행된다
- 객체를 바로 사용할 수 있는 상태로 만든다

```java
class Circle {
    int radius;
    String name;

    Circle(int r, String n) {
        radius = r;
        name = n;
    }
}

Circle pizza = new Circle(10, "자바피자");
```

실행 흐름:
1. `new Circle(10, "자바피자")` 실행
2. 생성자 호출
3. `radius = 10`, `name = "자바피자"` 초기화
4. 객체 생성 완료

잘못된 코드 (리턴 타입 사용 시 오류):
```java
public void Circle() { } // 컴파일 오류
```

---

## 4. 생성자 오버로딩

매개변수가 다른 생성자를 여러 개 정의할 수 있다. 생성자가 하나라도 선언되면 기본 생성자는 자동 생성되지 않으므로 필요하면 직접 만들어야 한다.

```java
public class Ex44Book {
    String title;
    String author;

    public Ex44Book(String t) {
        title = t;
        author = "작자미상";
    }

    public Ex44Book(String t, String a) {
        title = t;
        author = a;
    }

    public static void main(String[] args) {
        Ex44Book littlePrince = new Ex44Book("어린왕자", "생텍쥐페리");
        Ex44Book loveStory = new Ex44Book("춘향전");

        System.out.println(littlePrince.title + " " + littlePrince.author);
        System.out.println(loveStory.title + " " + loveStory.author);
    }
}
```

실행 결과:
```
어린왕자 생텍쥐페리
춘향전 작자미상
```

기본 생성자가 자동 생성되지 않는 경우:
```java
class Circle {
    public Circle(int r) { } // 생성자 존재
}

Circle c = new Circle(); // 오류 발생 - 기본 생성자 없음

// 해결: 직접 추가
class Circle {
    public Circle() { }       // 직접 추가
    public Circle(int r) { }
}
```

---

## 5. this 레퍼런스

현재 객체 자신을 가리키는 참조 변수다. 매개변수 이름과 필드 이름이 같을 때 구분하기 위해 사용한다.

```java
class Circle {
    int radius;

    public Circle(int radius) {
        this.radius = radius; // this.radius = 필드, radius = 매개변수
    }

    double getArea() {
        return 3.14 * this.radius * this.radius;
    }
}
```

`this` 없이 작성하면 둘 다 지역변수로 처리되어 의미 없는 코드가 된다:
```java
public Circle(int radius) {
    radius = radius; // 의미 없음 (둘 다 지역변수)
}
```

---

## 6. this()로 다른 생성자 호출

같은 클래스의 다른 생성자를 호출할 때 사용한다. 반드시 생성자의 첫 줄에 위치해야 한다.

```java
class Book {
    String title;
    String author;

    public Book() {
        this("제목없음", "작자미상"); // 반드시 첫 줄
    }

    public Book(String t, String a) {
        title = t;
        author = a;
    }
}
```

첫 줄이 아닌 경우 컴파일 오류:
```java
public Book() {
    System.out.println("생성자 호출");
    this("제목", "저자"); // 오류 - 첫 줄 아님
}
```

---

## 7. 객체 배열

객체 배열은 참조(주소)를 저장하는 배열이다. `new Circle[5]`는 배열(참조 공간)만 만들 뿐 실제 객체는 생성되지 않으므로, 각 요소에 별도로 `new`를 해야 한다.

```java
public class Ex46CircleArray {
    public static void main(String[] args) {

        Circle[] c;
        c = new Circle[5];

        for (int i = 0; i < c.length; i++) {
            c[i] = new Circle(i); // 객체 생성 필수
        }

        for (int i = 0; i < c.length; i++) {
            System.out.print((int)(c[i].getArea()) + " ");
        }
    }
}
```

실행 결과:
```
0 3 12 28 50
```

잘못된 코드:
```java
Circle[] c = new Circle[5];
c[0].getArea(); // 오류 - 객체 없음

// 올바른 코드
c[0] = new Circle(0);
c[0].getArea();
```

---

## 8. 메소드 (Method)

모든 메소드는 반드시 클래스 내부에 존재해야 한다.

```java
public int getSum(int i, int j) {
    int sum = i + j;
    return sum;
}
```

인자 전달 방식:

기본 타입 (값 전달 - Call by Value):
```java
static void increase(int m) {
    m = m + 1;
}

public static void main(String[] args) {
    int n = 10;
    increase(n);
    System.out.println(n); // 10 - 원본 변경 없음
}
```

배열 (참조 전달):
```java
static void increase(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i]++;
    }
}

public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5};
    increase(a);
    // 출력: 2 3 4 5 6 - 원본 변경됨
}
```

---

## 9. 메소드 오버로딩 vs 오버라이딩

| 구분 | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 위치 | 같은 클래스 내 | 부모 - 자식 클래스 간 |
| 메소드 이름 | 동일 | 동일 |
| 매개변수 | 달라야 함 | 동일해야 함 |
| 리턴 타입 | 무관 | 동일해야 함 |
| 결정 시점 | 컴파일 시 | 실행 시 (객체 기준) |
| 목적 | 같은 이름으로 다양한 기능 제공 | 부모 메소드를 자식이 재정의 |

오버로딩은 매개변수가 달라야 성립한다. 리턴 타입만 다른 경우는 오버로딩이 아니라 컴파일 오류다.

```java
// 오버로딩 성공
public int getSum(int i, int j) { return i + j; }
public int getSum(int i, int j, int k) { return i + j + k; }
public double getSum(double i, double j) { return i + j; }

// 오버로딩 실패 - 컴파일 오류
public int getSum(int i, int j) { return i + j; }
public double getSum(int i, int j) { return (double)(i + j); } // 매개변수 동일 → 오류
```

오버라이딩 예시:
```java
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("멍멍");
    }
}
```

`@Override` 어노테이션은 필수는 아니지만 부모 메소드 이름을 잘못 입력했을 때 컴파일러가 오류를 잡아주므로 붙이는 것이 권장된다.

---

## 10. 객체 치환과 가비지 컬렉션

객체 대입은 값 복사가 아니라 참조(주소) 복사다.

```java
Samp ob1 = new Samp(3);
Samp ob2 = new Samp(4);

ob1 = ob2; // ob1도 id=4 객체를 가리킴
           // 기존 id=3 객체는 참조 끊김 → 가비지

System.out.println("ob1.id=" + ob1.get()); // 4
System.out.println("ob2.id=" + ob2.get()); // 4
```

가비지 컬렉션:
- 참조가 끊긴 객체는 가비지가 되며 JVM이 자동으로 메모리를 회수한다
- C/C++과 달리 개발자가 직접 삭제할 수 없다
- `System.gc()`로 요청은 가능하지만 실행 시점은 JVM이 결정한다

```java
Person a = new Person("이몽룡");
Person b = new Person("성춘향");

b = a; // "성춘향" 객체는 참조 끊김 → 가비지
```

---

## 11. 접근 지정자

| 지정자 | 접근 범위 |
|---|---|
| private | 같은 클래스 내부만 |
| default (생략) | 같은 패키지 내 |
| protected | 같은 패키지 + 상속 관계 |
| public | 모든 클래스 |

범위 크기: `private` < `default` < `protected` < `public`

`default`는 키워드 없이 아무것도 쓰지 않는 것이며, `protected`는 상속 관계 포함임을 반드시 기억해야 한다.

---

## 12. static 멤버

클래스에 속하며 모든 객체가 공유하는 멤버다. 클래스 로딩 시 1번만 생성되며, 객체 없이 클래스 이름으로 직접 접근한다.

```java
class StaticSample {
    int n;              // non-static 필드
    void g() { }        // non-static 메소드

    static int m;       // static 필드
    static void f() { } // static 메소드
}

StaticSample.m = 10; // 클래스명으로 접근 (권장)
StaticSample.f();
```

| 구분 | static | non-static |
|---|---|---|
| 생성 시점 | 클래스 로딩 시 | 객체 생성 시 |
| 공유 여부 | 모든 객체가 공유 | 객체마다 별도 존재 |
| 접근 방법 | 클래스명으로 접근 | 객체로 접근 |

static 메소드의 제약:
- non-static 멤버에 접근 불가 (객체가 없기 때문)
- `this` 사용 불가 (객체가 없기 때문)

```java
static void s1(int x) {
    n = x;      // 오류 - non-static 필드 접근 불가
    f1(3);      // 오류 - non-static 메소드 호출 불가
    this.m = x; // 오류 - this 사용 불가
}
```

전역 변수와 전역 함수가 필요할 때, 또는 모든 객체가 공유해야 하는 값이 있을 때 `static`을 활용한다.

---

## 13. final 키워드

| 대상 | 의미 |
|---|---|
| final 클래스 | 상속 불가 (`extends` 불가) |
| final 메소드 | 오버라이딩 불가 |
| final 필드 | 값 변경 불가 (상수), 선언 시 초기화 필수 |

```java
// final 클래스
final class FinalClass { }
class DerivedClass extends FinalClass { } // 오류

// final 메소드
class SuperClass {
    protected final int finalMethod() { return 0; }
}
class SubClass extends SuperClass {
    protected int finalMethod() { return 1; } // 오류
}

// final 필드
public static final double PI = 3.14; // 선언과 동시에 초기화 필수
ROWS = 30; // 오류 - 값 변경 불가
```

---
## 6주차 정리 (4월 8일)

---

## 1. 배열의 크기 - length 필드

자바의 배열은 객체로 처리되며, 배열의 크기는 `length` 필드에 저장된다.

```java
int intArray[] = new int[5];
int size = intArray.length; // 5
```

`length`를 활용한 순회:
```java
for(int i=0; i<intArray.length; i++)
    System.out.println(intArray[i]);
```

> **추가 설명**
> `length`는 메소드가 아니라 필드(변수)다. `String`의 `length()`와 혼동하기 쉬운데, 배열은 괄호 없이 `length`만 쓴다.
> ```java
> int[] arr = new int[5];
> arr.length        // 배열 - 괄호 없음
> "hello".length()  // String - 괄호 있음
> ```

> **실수하기 쉬운 포인트**
> `length`는 배열에 실제로 값이 들어있는 개수가 아니라 선언된 크기를 반환한다. `new int[5]`로 만들면 아무것도 안 넣어도 `length`는 5다.

---

## 2. 함수 호출 시 배열 전달 - C/C++ vs Java

C/C++는 배열과 크기를 별도로 전달해야 하지만, 자바는 배열만 전달해도 `length`로 크기를 알 수 있다.

```java
// Java
int sum(int x[]) {
    int s = 0;
    for(int n=0; n < x.length; n++)
        s += x[n];
    return s;
}
```

> **추가 설명**
> 자바에서 배열을 메소드에 전달하면 배열 전체가 복사되는 게 아니라 참조(주소)가 전달된다. 따라서 메소드 내에서 배열 원소를 수정하면 원본 배열도 바뀐다.
> ```java
> void addOne(int[] arr) {
>     arr[0] = 999; // 원본도 바뀜
> }
> ```

> **실수하기 쉬운 포인트**
> C처럼 "배열을 넘기면 복사본이 생긴다"고 생각하면 안 된다. 같은 배열을 가리키고 있어서 의도치 않게 원본을 수정할 수 있다.

---

## 3. for-each 문

배열이나 열거형의 원소를 순차적으로 접근하는 간결한 for문이다.

```java
int[] n = {1,2,3,4,5};
int sum = 0;

for(int k : n) {
    sum += k;
}
```

반복마다 `k`는 `n[0]`, `n[1]`, ..., `n[4]` 순서로 할당된다.

**예제 - 정수 배열 합산 및 문자열 배열 출력:**

```java
public class Ex39ForeachEx {
    public static void main(String[] args) {
        int[] n = { 1, 2, 3, 4, 5 };
        int sum = 0;

        for (int k : n) {
            System.out.print(k + " ");
            sum += k;
        }
        System.out.println("합은 " + sum);

        String f[] = { "사과", "배", "바나나", "체리", "딸기", "포도" };
        for (String s : f) {
            System.out.print(s + " ");
        }
    }
}
```

| 항목 | 내용 |
|---|---|
| 예상 출력 | `1 2 3 4 5 합은 15` / `사과 배 바나나 체리 딸기 포도` |

> **추가 설명**
> for-each는 읽기 전용 순회에 적합하다. 내부적으로 인덱스를 따로 관리하지 않기 때문에 현재 몇 번째 원소인지 알 수 없다. 인덱스가 필요한 경우에는 일반 for문을 써야 한다.

> **실수하기 쉬운 포인트**
> for-each 내부에서 변수에 값을 대입해도 원본 배열이 바뀌지 않는다.
> ```java
> for (int k : n) {
>     k = 0; // 원본 배열 n은 그대로
> }
> ```
> `k`는 배열 원소의 복사본이기 때문에 원본을 수정하려면 일반 for문으로 인덱스를 사용해야 한다.

---

## 4. 2차원 배열

**선언 및 생성:**

```java
int intArray[][] = new int[2][5];
```

**length 필드:**

```java
int i[][] = new int[2][5];
i.length       // 2 (행의 수)
i[0].length    // 5 (0번째 행의 열 수)
```

**Ragged Array (비정형 배열):**

자바의 2차원 배열은 실제로 배열 안에 배열이므로 행마다 열 개수가 달라도 된다.

```java
int[][] arr = new int[2][];
arr[0] = new int[3];
arr[1] = new int[5];
```

**선언과 동시에 초기화:**

```java
int intArray[][] = {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
};
```

> **추가 설명**
> 자바의 2차원 배열은 C처럼 메모리가 연속적으로 배치된 진짜 2D 구조가 아니다. 각 행이 독립적인 1차원 배열 객체이기 때문에 행마다 크기가 달라도 된다. 이것이 Ragged Array가 가능한 이유다.
>
> 2차원 배열 전체를 순회할 때는 이중 for문을 사용한다.
> ```java
> for(int r=0; r<intArray.length; r++) {
>     for(int c=0; c<intArray[r].length; c++) {
>         System.out.print(intArray[r][c] + " ");
>     }
> }
> ```
> `intArray[r].length`를 쓰면 Ragged Array에서도 안전하게 순회할 수 있다.

> **실수하기 쉬운 포인트**
> `intArray.length`는 행의 수고, 열의 수는 `intArray[0].length`다. 헷갈려서 행과 열을 반대로 쓰면 `ArrayIndexOutOfBoundsException`이 발생한다.

---

## 5. 메소드의 배열 리턴

배열 전체가 복사되는 것이 아니라 참조(주소)만 반환된다. 메소드 내 지역변수가 사라져도 Heap에 생성된 배열은 유지된다.

```java
int[] makeArray() {
    int temp[] = new int[4];
    return temp; // 참조 반환
}

int[] intArray = makeArray(); // 같은 배열을 가리킴
```

실제 배열이 사라지는 경우는 어떤 변수도 해당 배열을 참조하지 않을 때이며, 그 시점에 GC(Garbage Collector) 대상이 된다.

> **추가 설명**
> 지역변수 `temp`는 Stack에 저장되고, `new int[4]`로 생성된 실제 배열은 Heap에 저장된다. 메소드가 종료되면 Stack의 `temp`는 사라지지만 Heap의 배열은 살아있고, `intArray`가 그 주소를 이어받아 참조하게 된다.

> **실수하기 쉬운 포인트**
> 리턴 타입 선언 시 배열 크기를 지정하지 않는다.
> ```java
> int[4] makeArray() { ... } // 컴파일 에러
> int[]  makeArray() { ... } // 올바른 선언
> ```

---

## 6. 예외 처리 (Exception Handling)

**예외란:** 실행 중 발생하는 예상치 못한 오류 상황이다. 처리하지 않으면 프로그램이 강제 종료된다.

**주요 예외 클래스:**

| 예외 클래스 | 발생 상황 | 패키지 |
|---|---|---|
| ArithmeticException | 정수를 0으로 나눌 때 | java.lang |
| NullPointerException | null 참조 접근 시 | java.lang |
| ArrayIndexOutOfBoundsException | 배열 범위 초과 접근 시 | java.lang |
| ClassCastException | 잘못된 타입 변환 시 | java.lang |
| OutOfMemoryError | 메모리 부족 시 | java.lang |
| IllegalArgumentException | 잘못된 인자 전달 시 | java.lang |
| IOException | 입출력 실패 시 | java.io |
| NumberFormatException | 숫자 형식 불일치 변환 시 | java.lang |
| InputMismatchException | Scanner 입력 타입 불일치 시 | java.util |

**try-catch-finally 구조:**

```java
try {
    // 예외 발생 가능 코드
}
catch (처리할예외타입 e) {
    // 예외 처리 코드
}
finally {
    // 예외 발생 여부와 무관하게 항상 실행
}
```

**예제 - 0으로 나누기 예외 처리:**

```java
public class Ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dividend, divisor;

        System.out.print("나눔수를 입력하시오:");
        dividend = scanner.nextInt();

        System.out.print("나눗수를 입력하시오:");
        divisor = scanner.nextInt();

        try {
            System.out.println(dividend + "를 " + divisor + "로 나누면 몫은 "
                    + dividend / divisor + "입니다.");
        }
        catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다!");
        }
        finally {
            scanner.close();
        }
    }
}
```

| 항목 | 내용 |
|---|---|
| 정상 입력 | 몫 출력 후 scanner 닫힘 |
| 0 입력 시 | catch 블록 실행 → "0으로 나눌 수 없습니다!" 출력 |

> **추가 설명**
> `finally` 블록은 try에서 예외가 발생하든 안 하든, 심지어 catch에서 또 다른 예외가 발생해도 반드시 실행된다. `scanner.close()`처럼 반드시 해제해야 하는 자원 정리 코드를 넣기에 적합하다.
>
> `catch` 블록의 `e`를 통해 예외 정보를 출력할 수 있다.
> ```java
> catch (ArithmeticException e) {
>     System.out.println(e.getMessage());
> }
> ```

> **실수하기 쉬운 포인트**
> catch에 선언한 예외 타입과 실제 발생한 예외가 일치하거나 부모-자식 관계여야 처리된다. 타입이 다르면 catch를 통과해서 프로그램이 종료된다.
>
> `finally`는 `return`문 이후에도 실행된다는 점에 주의해야 한다.

---

## 7. 객체 지향 프로그래밍 (OOP)

### 캡슐화 (Encapsulation)

객체를 캡슐로 감싸 내부를 외부로부터 보호하는 것. `class`가 그 틀이 된다.

```java
class Animal {
    String name;
    int age;
    void eat() { ... }
    void speak() { ... }
}
```

> **추가 설명**
> 캡슐화의 핵심은 접근 제한자(`private`, `public`, `protected`)를 활용해 외부에서 함부로 내부 데이터를 건드리지 못하게 하는 것이다. 필드는 `private`으로 막고, 필요한 경우에만 `getter/setter` 메소드를 통해 접근하게 하는 패턴이 일반적이다.
> ```java
> class Animal {
>     private String name;
>
>     public String getName() { return name; }
>     public void setName(String n) { name = n; }
> }
> ```

### 상속 (Inheritance)

상위 클래스(슈퍼 클래스)의 멤버를 하위 클래스(서브 클래스)가 물려받는 것. `extends` 키워드 사용. is-a 관계일 때만 사용해야 한다.

```java
class Human extends Animal {
    String hobby;
    void work() { ... }
}
```

> **추가 설명**
> 자바는 단일 상속만 지원한다. 하나의 클래스는 하나의 부모 클래스만 가질 수 있으며, 다중 상속이 필요한 경우에는 인터페이스(`interface`)를 사용한다.

> **실수하기 쉬운 포인트**
> is-a 관계가 아닌데 코드 재사용만을 목적으로 상속을 쓰면 설계가 엉켜버린다. 단순 재사용이 목적이라면 상속보다 포함(has-a) 관계를 쓰는 게 낫다.

### 다형성 (Polymorphism)

같은 이름의 메소드가 클래스나 객체에 따라 다르게 동작하는 것.

**오버로딩 vs 오버라이딩:**

| 구분 | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 발생 위치 | 같은 클래스 내 | 상속 관계 |
| 조건 | 메소드 이름 같고, 매개변수 다름 | 부모 메소드를 자식이 재정의 |
| 결정 시점 | 컴파일 시 | 실행 시 (객체 기준) |

```java
// 오버로딩
class Test {
    void print(int a) {}
    void print(String a) {}
}

// 오버라이딩
class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("멍멍");
    }
}
```

> **추가 설명**
> 오버라이딩 시 `@Override` 어노테이션은 필수는 아니지만 붙이는 것이 강력히 권장된다. 부모 메소드 이름을 잘못 입력했을 때 컴파일러가 오류를 잡아주기 때문이다.

> **실수하기 쉬운 포인트**
> 오버로딩은 반환 타입만 다르고 매개변수가 같으면 성립하지 않는다. 반드시 매개변수의 타입이나 개수가 달라야 한다.
> ```java
> int print(int a) {}
> void print(int a) {} // 컴파일 에러 - 오버로딩 아님
> ```

---

## 8. 클래스와 객체

| 구분 | 설명 |
|---|---|
| 클래스 | 객체의 속성과 행위를 정의한 설계도 |
| 객체 | 클래스로 생성된 실체, 메모리 공간을 가짐. 인스턴스(instance)라고도 함 |

클래스는 필드(멤버 변수)와 메소드(멤버 함수)로 구성되며, `public` 접근 지정자를 통해 외부 클래스의 접근을 허용할 수 있다.

> **추가 설명**
> 객체는 `new` 키워드로 생성하며, 생성할 때마다 독립적인 메모리 공간(Heap)이 할당된다. 같은 클래스로 만든 객체라도 각자 독립적인 필드 값을 가진다.
> ```java
> Animal a1 = new Animal();
> Animal a2 = new Animal();
> a1.name = "강아지";
> a2.name = "고양이";
> // a1과 a2는 서로 영향을 주지 않음
> ```

> **실수하기 쉬운 포인트**
> 객체를 선언만 하고 생성하지 않으면 `null` 상태가 된다. 이 상태에서 필드나 메소드에 접근하면 `NullPointerException`이 발생한다.
> ```java
> Animal a;
> a.name = "강아지"; // NullPointerException 발생
>
> Animal a = new Animal(); // 이렇게 생성해야 함
> ```

---
## 5주차 수업 (4월 1일)

## 1. 비교 연산자

```java
a < b      // a가 b보다 작으면 true
a > b      // a가 b보다 크면 true
a <= b     // a가 b보다 작거나 같으면 true
a >= b     // a가 b보다 크거나 같으면 true
a == b     // a가 b와 같으면 true
a != b     // a가 b와 같지 않으면 true
```

---

## 2. 논리 연산자

```java
!a         // a가 true면 false, false면 true
a ^ b      // XOR (서로 다르면 true, 같으면 false)
a || b     // OR (둘 중 하나라도 true면 true)
a && b     // AND (둘 다 true일 때만 true)
```

---

## 3. 조건 연산자 (삼항 연산자)

- 3개의 피연산자로 구성된 연산자 (ternary)
- `if-else`를 간결하게 표현 가능

**기본 구조**
```java
opr1 ? opr2 : opr3
// opr1이 true → opr2
// opr1이 false → opr3
```

**if-else vs 삼항 연산자 비교**
```java
// if-else 방식
int x = 5;
int y = 3;
int big;

if (x > y)
    big = x;
else
    big = y;

// 삼항 연산자 방식
int big = (x > y) ? x : y;
```

**코드 예제**
```java
public class TernaryOperator29 {

    public static void main(String[] args) {
        int a = 3, b = 5;

        System.out.println("두 수의 차는 " + ((a > b) ? (a - b) : (b - a)));
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `a = 3, b = 5` | a가 b보다 작으므로 `a > b`는 false |
| `(a > b) ? (a - b) : (b - a)` | false이므로 `b - a = 5 - 3 = 2` 반환 |

**실행 결과**
```
두 수의 차는 2
```

---

## 4. 비트 연산

**비트 기본 개념**
```java
byte x = 10;
// x → 00001010 (1바이트 = 8비트)
```

**비트 연산 활용 예시**

```java
// 1. 성능 최적화 - 곱셈/나눗셈 대체
int x = 5;
int result = x << 1;  // 5 * 2 = 10
System.out.println(result);
```
```
10
```

```java
// 2. 비트 마스크 - 권한 플래그 설정
class Permissions {
    static final int READ = 1;   // 0001
    static final int WRITE = 2;  // 0010
    static final int EXEC = 4;   // 0100
}

int userPermissions = Permissions.READ | Permissions.WRITE; // 0011 (3)
boolean canWrite = (userPermissions & Permissions.WRITE) != 0;
System.out.println("Can Write: " + canWrite);
```
```
Can Write: true
```

```java
// 3. RGB 색상 데이터 압축
int red = 255;
int green = 128;
int blue = 64;
int packedData = (red << 16) | (green << 8) | blue;
```

```java
// 4. 짝수/홀수 판별
int num = 10;
boolean isEven = (num & 1) == 0;

// 5. 부호 바꾸기 (2의 보수)
int x = 5;
int negativeX = ~x + 1;
```

---

## 5. 조건문

### 5-1. 단순 if문

```java
if (조건식) {
    // 실행 문장 (조건식이 참인 경우)
}

// 예제 1
int n = 10;
if (n % 2 == 0) {
    System.out.print(n);
    System.out.println("은 짝수입니다.");
}

// 예제 2
int score = 85;
if (score >= 80 && score <= 89)
    System.out.println("학점은 B입니다.");
```

### 5-2. if-else 문

```java
if (조건식) {
    // 실행 문장1 (참인 경우)
} else {
    // 실행 문장2 (거짓인 경우)
}

// 예제
int score2 = 90;
if (score2 >= 90) {
    System.out.println("합격입니다.");
} else {
    System.out.println("불합격입니다.");
}
```

**코드 예제**
```java
package week05;

import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("나이를 입력하시오:");
        int age = scanner.nextInt();
        if ((age >= 20) && (age < 30)) {
            System.out.println("20대입니다.");
            System.out.println("20대라서 행복합니다!.");
        } else {
            System.out.println("20대가 아닙니다.");
        }
        scanner.close();
    }
}
```

**분석**

| 조건 | 내용 |
|------|------|
| `age >= 20 && age < 30` | 입력값이 20 이상 30 미만이면 true |
| true일 때 | "20대입니다." + "20대라서 행복합니다!." 출력 |
| false일 때 | "20대가 아닙니다." 출력 |

**실행 결과** (입력: `25`)
```
나이를 입력하시오:25
20대입니다.
20대라서 행복합니다!.
```

### 5-3. 다중 if-else 문

```java
if (조건식1) {
    // 실행 문장1
} else if (조건식2) {
    // 실행 문장2
} else if (조건식3) {
    // 실행 문장3
} else {
    // 앞의 모든 조건이 거짓인 경우
}
```

> 조건문이 너무 많은 경우 switch 문 사용 권장. 실행 문장이 실행되면 이후 else 블록은 건너뜀.

---

## 6. switch 문

```java
switch (식) {
    case 값1:
        // 실행 문장1
        break;
    case 값2:
        // 실행 문장2
        break;
    default:
        // 어떤 case와도 같지 않을 때 실행
}
```

- `break`를 만나면 switch 문 탈출
- 일치하는 case가 없으면 `default` 실행
- `default`는 생략 가능

**예제**
```java
char grade = 'B';

switch (grade) {
    case 'A':
        System.out.println("축하합니다.");
        System.out.println("잘했습니다.");
        break;
    case 'B':
        System.out.println("좋아요.");
        break;
    case 'C':
        System.out.println("노력하세요.");
        break;
    default:
        System.out.println("탈락입니다!");
}
```

**실행 결과**
```
좋아요.
```

**case 값 허용 범위**

| 허용 | 불허 |
|------|------|
| 정수 리터럴 | 실수 리터럴 |
| 문자 리터럴 | 변수 |
| 문자열 리터럴 (JDK 1.7+) | 수식 (예: `a > 3`) |

**코드 예제 - 계절 판별**
```java
package week05;

import java.util.Scanner;

public class Ex13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("월(1~12)을 입력하시오:");
        int month = scanner.nextInt();

        switch (month) {
            case 3:
            case 4:
            case 5:
                System.out.println("봄입니다.");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("여름입니다.");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("가을입니다.");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("겨울입니다.");
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `month = 7` (예시) | case 6, 7, 8 중 7에 해당 |
| fall-through | case 6, 7은 break 없이 case 8로 이어지다가 "여름입니다." 출력 후 break |
| 1~12 외 값 | default 실행 |

**실행 결과** (입력: `7`)
```
월(1~12)을 입력하시오:7
여름입니다.
```

---

## 7. 반복문

### 7-1. while 문

```java
// 기본 구조
while (조건식) {
    // 조건식이 참인 동안 반복 실행
}

// 예시
int i = 0;
while (i < 10) {
    System.out.print(i);
    i++;
}
```
```
0123456789
```

**코드 예제 - 평균 구하기**
```java
import java.util.Scanner;

public class Ex32WhileSample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = 0, n = 0;
        double sum = 0;

        System.out.println("정수를 입력하고 마지막에 0을 입력하세요.");

        while ((n = scanner.nextInt()) != 0) {
            sum = sum + n;
            count++;
        }

        System.out.print("수의 개수는 " + count + "개이며 ");
        System.out.println("평균은 " + (sum / count) + "입니다.");

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `n = scanner.nextInt()` | 입력값을 n에 저장하면서 동시에 0인지 비교 |
| `n != 0` | 0이 아니면 반복 계속, 0이면 탈출 |
| `sum += n`, `count++` | 합계와 개수 누적 |
| `sum / count` | 반복 종료 후 평균 출력 |

**실행 결과** (입력: `10 20 30 0`)
```
정수를 입력하고 마지막에 0을 입력하세요.
수의 개수는 3개이며 평균은 20.0입니다.
```

### 7-2. do-while 문

```java
// 기본 구조 - 작업문이 최소 한 번 반드시 실행됨
do {
    // 작업문
} while (조건식);

// 예시
int i = 0;
do {
    System.out.print(i);
    i++;
} while (i < 10);
```
```
0123456789
```

### 7-3. 중첩 반복

```java
for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 10000; j++) {
        // 작업 수행
    }
}
```

**코드 예제 - 구구단 (가로 출력)**
```java
package week05;

public class Ex04 {
    public static void main(String[] args) {

        for (int i = 2; i <= 9; i++) {
            System.out.print("[" + i + "단]\t");
        }
        System.out.println();

        for (int j = 1; j <= 9; j++) {
            for (int i = 2; i <= 9; i++) {
                System.out.print(i + "X" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| 첫 번째 for | 단 제목(2단~9단) 가로로 출력 |
| 바깥 for (`j`) | 행(1~9) 기준, 세로 반복 |
| 안쪽 for (`i`) | 열(2단~9단) 기준, 가로 반복 |
| `\t` | 탭으로 정렬 |

**실행 결과**
```
[2단]   [3단]   [4단]   [5단]   [6단]   [7단]   [8단]   [9단]
2X1=2   3X1=3   4X1=4   5X1=5   6X1=6   7X1=7   8X1=8   9X1=9
2X2=4   3X2=6   4X2=8   5X2=10  6X2=12  7X2=14  8X2=16  9X2=18
...
2X9=18  3X9=27  4X9=36  5X9=45  6X9=54  7X9=63  8X9=72  9X9=81
```

**코드 예제 - 구구단 (단 별 출력)**
```java
package week05;

public class Ex04r {
    public static void main(String[] args) {

        for (int i = 2; i <= 9; i++) {
            System.out.println("[" + i + "단]");

            for (int j = 1; j <= 9; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }

            System.out.println();
        }
    }
}
```

**실행 결과**
```
[2단]
2 * 1 = 2
2 * 2 = 4
...
2 * 9 = 18

[3단]
3 * 1 = 3
...
```

---

## 8. continue 문

```java
// 다음 반복으로 넘어감 (아래 코드 건너뜀)

// 예제: 양수 합 구하기
import java.util.Scanner;

public class Ex35ContinueExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("정수를 5개 입력하세요.");
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            int n = scanner.nextInt();

            if (n <= 0) continue;  // 0 또는 음수는 건너뜀
            else sum += n;         // 양수만 더함
        }

        System.out.println("양수의 합은 " + sum);

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `n <= 0` | 0 이하인 경우 continue로 이번 반복 건너뜀 |
| `sum += n` | 양수인 경우에만 합산 |

**실행 결과** (입력: `3 -1 0 5 2`)
```
정수를 5개 입력하세요.
양수의 합은 10
```

---

## 9. break 문

```java
// 가장 가까운 반복문 하나를 즉시 종료

// 예제 1: 단일 반복문
for (int i = 1; i <= 10; i++) {
    if (i == 5) break;
    System.out.println(i);
}
```
```
1
2
3
4
```

```java
// 예제 2: 중첩 반복 - 안쪽 반복문만 종료
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 5; j++) {
        if (j == 3) break;
        System.out.println("i=" + i + ", j=" + j);
    }
}
```
```
i=1, j=1
i=1, j=2
i=2, j=1
i=2, j=2
i=3, j=1
i=3, j=2
```

**코드 예제 - 입력 종료**
```java
import java.util.Scanner;

public class Ex36BreakExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("exit를 입력하면 종료합니다.");

        while (true) {
            System.out.print(">>");
            String text = scanner.nextLine();

            if (text.equals("exit"))
                break;
        }

        System.out.println("종료합니다...");
        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `while (true)` | 무한 루프 |
| `text.equals("exit")` | 입력값이 "exit"이면 break로 탈출 |
| `scanner.nextLine()` | 공백 포함 한 줄 전체 읽음 |

**실행 결과** (입력: `hello`, `exit`)
```
exit를 입력하면 종료합니다.
>>hello
>>exit
종료합니다...
```

---

## 10. 배열 (Array)

- 인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조
- 같은 타입의 데이터들이 순차적으로 저장됨
- 인덱스는 0부터 시작
- 반복문을 이용하여 처리하기에 적합한 자료 구조

### 10-1. 배열 선언과 생성

```java
// (1) 선언과 생성 분리
int intArray[];          // 선언
intArray = new int[5];   // 생성

// (2) 선언과 생성 동시에
int[] intArray = new int[5];

// (3) 선언과 동시에 초기화
int[] intArray = {4, 3, 2, 1, 0};
double[] doubleArray = {0.01, 0.02, 0.03, 0.04};

// 잘못된 선언 (오류)
int intArray[5];  // 선언 시 크기 지정 불가
```

### 10-2. 배열 인덱스와 원소 접근

```java
int[] intArray = new int[5];

intArray[0] = 5;  // 인덱스 0에 5 저장
intArray[3] = 6;  // 인덱스 3에 6 저장

int n = intArray[3];  // 인덱스 3의 값 읽기 → n = 6

// 잘못된 접근 (오류)
int x = intArray[-2];  // 음수 인덱스 불가
int y = intArray[5];   // 범위 초과 (0~4까지)

// 생성 전 접근 (오류)
int[] arr;
arr[1] = 8;  // NullPointer 오류

// 올바른 방식
arr = new int[5];
arr[1] = 8;
```

**코드 예제 - 최대값 찾기**
```java
package week05;

import java.util.Scanner;

public class Ex07 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int intArray[];
        intArray = new int[5];

        int max = 0;

        System.out.println("양수 5개를 입력하세요.");

        for (int i = 0; i < 5; i++) {
            intArray[i] = scanner.nextInt();

            if (intArray[i] > max) {
                max = intArray[i];
            }
        }

        System.out.println("가장 큰 수는 " + max + "입니다.");

        scanner.close();
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `max = 0` | 최대값 초기화 (입력이 모두 양수라는 전제) |
| `intArray[i] = scanner.nextInt()` | 입력값을 배열에 순서대로 저장 |
| `if (intArray[i] > max)` | 현재 입력값이 기존 최대값보다 크면 갱신 |

**실행 결과** (입력: `3 7 2 9 5`)
```
양수 5개를 입력하세요.
가장 큰 수는 9입니다.
```

**코드 예제 - for문 합산**
```java
package week05;

public class Ex01 {
    public static void main(String[] args) {

        int i, sum = 0;
        for (i = 1; i <= 10; i++) {
            sum += i;
            System.out.println(i);
            if (i < 9)
                System.out.println("+");
            else {
                System.out.println("=");
                System.out.println(sum);
            }
        }
    }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `sum += i` | 1부터 i까지 누적 합산 |
| `i < 9` | 9 미만이면 "+" 출력 |
| `i >= 9` (else) | 9, 10일 때 "=" 출력 후 누적합 출력 |

**실행 결과**
```
1
+
2
+
3
+
4
+
5
+
6
+
7
+
8
+
9
=
45
10
=
55
```

---

## 4주차 수업 (3월 25일)

## 1. 자바의 특징 (계속)

### 가비지 컬렉션 (Garbage Collection)

- 자바 언어는 메모리 할당 기능은 있어도 **메모리 반환 기능 없음**
- 사용하지 않는 메모리는 **JVM에 의해 자동 반환 — 가비지 컬렉션(GC)**
- 개발자가 직접 메모리를 해제하지 않아도 됨 (C/C++과의 차이점)

> **실시간 응용프로그램에 부적합한 이유**: 실행 도중 **예측할 수 없는 시점**에 GC가 실행되어 응용프로그램이 일시적으로 중단될 수 있음

---

### 안전한 프로그램

- **타입 체크가 엄격**하여 잘못된 타입 사용 시 컴파일 단계에서 오류 발생
- **포인터(Pointer) 개념 없음** → 물리적 메모리 주소 직접 접근 불가 → 메모리 오염 방지

---

### 프로그램 작성 용이

- 포인터 개념 없음
- 동적 메모리 직접 반환 불필요 (GC가 처리)
- 다양한 라이브러리(Java API) 지원

---

### JIT 컴파일러 (Just-In-Time Compiler)

- 자바는 기본적으로 바이트코드를 **인터프리터 방식**으로 실행 → 기계어 직접 실행보다 느림
- **JIT 컴파일 기법**으로 실행 속도 개선
  - 실행 중에 바이트코드를 **기계어 코드로 컴파일**하여 실행
  - 자주 실행되는 코드는 컴파일된 기계어로 캐싱되어 반복 실행 시 빠름

---

## 2. 소스코드 · 바이트코드 · 기계어 정리

| 구분 | 파일 | 실행 주체 | 플랫폼 종속성 |
|------|------|-----------|---------------|
| 소스코드 | `.java` | 사람이 읽음 | 없음 |
| 바이트코드 | `.class` | JVM | 없음 (독립적) |
| 기계어 | 없음 (메모리) | CPU 직접 실행 | 있음 (CPU/OS 종속) |

- **소스코드**: 우리가 작성하는 Java 코드 (`.java` 파일), 사람이 읽을 수 있는 고수준 언어
- **바이트코드**: `javac`가 소스코드를 변환한 중간 코드 (`.class` 파일)
  - CPU가 직접 실행 불가 → **JVM이 인터프리터**로 해석하거나 **JIT 컴파일러**가 기계어로 변환하여 실행
  - 플랫폼 독립적 (Windows, Mac, Linux 동일하게 실행 가능)
- **기계어**: CPU가 직접 실행하는 0과 1의 이진 코드, OS와 CPU 아키텍처(Intel, ARM 등)에 따라 다름

---

## 3. Temurin OpenJDK 24에서 jmods 디렉토리가 사라진 이유

- **JEP 493** 표준을 따르게 되어 `jmods` 디렉토리가 포함되지 않음
- JEP 493 요약:
  - `jlink` 툴이 **JMOD 파일 없이도** 사용자 지정 런타임 이미지(Custom run-time image) 생성 가능
  - JDK 크기 약 **25% 절감** 효과
  - 기본적으로 비활성화이나, **Temurin OpenJDK 24는 빌드 시 자동 활성화**

---

## 4. 식별자 (Identifier) — 명명 규칙

> 클래스, 변수, 상수, 메소드 등에 붙이는 이름

| 규칙 | 설명 |
|------|------|
| 사용 가능 특수문자 | `_`, `$` 만 허용 (`@`, `#`, `!` 등 불가) |
| 공백·탭 | 사용 불가 |
| 유니코드 | 한글 포함 가능 (실무에서는 사용 금지) |
| 첫 문자 | 숫자 사용 불가, `_`·`$`는 가능하나 지양 |
| 키워드 사용 | `int`, `class`, `for` 등 예약어 사용 불가 |
| 리터럴 사용 | `true`, `false`, `null` 사용 불가 |
| 길이 | 제한 없음 |
| 대소문자 | 구별함 (`barChart` ≠ `barchart`) |

---

## 5. Java 기본 자료형 (Primitive Type) 8개

| 타입 | 크기 | 범위 / 특징 |
|------|------|-------------|
| `boolean` | 1비트 | `true` 또는 `false` |
| `char` | 2바이트 | Unicode 문자 (0 ~ 65535) |
| `byte` | 1바이트 | -128 ~ 127 |
| `short` | 2바이트 | -32,768 ~ 32,767 |
| `int` | 4바이트 | -2³¹ ~ 2³¹-1 |
| `long` | 8바이트 | -2⁶³ ~ 2⁶³-1 |
| `float` | 4바이트 | 약 ±3.4E38 |
| `double` | 8바이트 | 약 ±1.7E308 |

> Java 기본 타입 크기는 **CPU나 운영체제와 무관하게 고정**됨 (C/C++과의 차이)

---

## 6. 참조 자료형 (Reference Type)

- **레퍼런스형 1개**, 용도는 클래스 / 인터페이스 / 배열에 대한 참조
- 포인터와 유사하나, **임의의 메모리 주소를 직접 저장할 수 없음** → JVM이 주소를 관리
- 객체는 **힙(Heap) 영역**에 저장, 참조 변수는 해당 주소를 가리킴
- `new` 키워드로 객체(인스턴스) 생성
- 객체를 참조하지 않을 때 `null` 값 가질 수 있음
- 같은 객체를 여러 변수가 참조 가능, `==` 연산자로 주소 비교 가능
- 문자열은 기본 타입이 아님 → **`String` 클래스**로 표현

> **배열(Array), 인터페이스(Interface), 열거형(Enum)** 도 객체이므로 참조 자료형

---

## 7. 메모리 구조
```
[ 스택 (Stack) ]          [ 힙 (Heap) ]
- LIFO 구조               - FIFO 구조
- 기본 자료형 저장         - 참조 자료형(객체) 저장
- 메소드 호출 시 자동 생성  - new 키워드로 생성
- 메소드 종료 시 자동 소멸  - GC가 자동 반환
```

- **힙 오버플로우**: 힙이 스택 영역을 침범하는 경우
- **스택 오버플로우**: 스택이 힙 영역을 침범하는 경우 (재귀 무한 호출 등)

---

## 8. 변수 (Variable) vs 상수 (Constant)

| 구분 | 선언 | 값 변경 |
|------|------|---------|
| 변수 | `int a = 10;` | 가능 |
| 상수 | `final int A = 10;` | 불가 (컴파일 에러) |

- 상수는 `final` 키워드 사용
- 상수명은 관례상 **대문자 + 언더스코어** 사용 (예: `MAX_SIZE`)

---

## 9. var 키워드 (Java 10+)

- **타입 추론**: 컴파일러가 초기값을 보고 타입 자동 결정
- **지역 변수 전용** (클래스 필드에서는 사용 불가)
- 초기값 없이 선언하면 **컴파일 오류** 발생
```java
var list = new ArrayList<String>(); // ArrayList<String>으로 추론
var num = 10;                        // int로 추론
```

> **권장 사용 기준**: 가독성이 유지되는 경우에만 사용. 기본적으로는 명시적 자료형(`int`, `String` 등) 사용 권장

---

## 10. System.out.print 종류

| 메소드 | 줄 바꿈 | 특징 |
|--------|---------|------|
| `System.out.print()` | 없음 | 줄 바꿈 없음. `\n` 직접 삽입 필요 |
| `System.out.println()` | 있음 | 자동 줄 바꿈 (가장 많이 사용) |
| `System.out.printf()` | 없음 | 형식 지정(formatting) 출력 |

### printf 형식 지정자

| 지정자 | 타입 | 예시 |
|--------|------|------|
| `%d` | 정수 | `printf("%d", 10)` → `10` |
| `%f` | 실수 | `printf("%.2f", 3.1415)` → `3.14` |
| `%s` | 문자열 | `printf("%s", "Hello")` → `Hello` |
| `%c` | 문자 | `printf("%c", 'A')` → `A` |
| `%b` | 불리언 | `printf("%b", true)` → `true` |

---

## 11. 타입 변환

### 자동 타입 변환 (묵시적)

- 컴파일러가 **작은 타입 → 큰 타입**으로 자동 변환
- 데이터 손실 없음
```java
long m = 25;           // int → long 자동 변환
double d = 3.14 * 10;  // int 10 → double 10.0 자동 변환
```

### 강제 타입 변환 (명시적, 캐스팅)

- 개발자가 `(타입)` 형태로 직접 지정
- **큰 타입 → 작은 타입** 변환 시 **값 손실** 가능
```java
int i = (int) 2.9;   // 2 (소수점 버림)
byte b = (byte) 200; // 값 손실 발생
```

> `int → byte` 등 **큰 타입에서 작은 타입으로는 자동 변환되지 않음** → 컴파일 오류

---

## 12. System.in vs Scanner

### System.in

- 키보드와 연결된 **표준 입력 스트림**
- 입력된 키를 **바이트(byte) 단위**로 반환 (저수준 스트림)
- 바이트를 직접 문자/숫자로 변환하기 어려워 **직접 사용하기 불편**

### Scanner 클래스

- `System.in`을 감싸는 **고수준 입력 처리 클래스**
- 읽은 바이트를 문자, 정수, 실수, 불린, 문자열 등 **다양한 타입으로 변환하여 반환**
- 입력을 **공백 문자(`\t`, `\r`, `\n`, ` `, `\f`)** 기준으로 토큰 단위로 분리

| 구분 | System.in | Scanner |
|------|-----------|---------|
| 반환 타입 | byte | int, String, double 등 다양 |
| 사용 난이도 | 어려움 | 쉬움 |
| 실무 사용 빈도 | 낮음 | 높음 |
| 임포트 필요 | 없음 | `import java.util.Scanner` 필요 |

> **실무 및 학습에서는 Scanner를 훨씬 많이 사용**. `System.in`은 성능이 중요한 저수준 I/O나 커스텀 파싱이 필요한 경우에 사용

---

## 13. 자바 연산자 전체 정리

### 13-1. 산술 연산자

| 연산자 | 의미 | 예시 | 결과 |
|--------|------|------|------|
| `+` | 덧셈 (문자열 연결) | `3 + 2` / `"Hi" + 1` | `5` / `"Hi1"` |
| `-` | 뺄셈 | `3 - 2` | `1` |
| `*` | 곱셈 | `3 * 2` | `6` |
| `/` | 나눗셈 (정수끼리면 몫만) | `10 / 4` / `10.0 / 4` | `2` / `2.5` |
| `%` | 나머지 | `10 % 3` | `1` |

---

### 13-2. 증감 연산자

| 연산자 | 의미 | 예시 |
|--------|------|------|
| `++a` | 전위 증가: 먼저 +1, 그 다음 사용 | `int a=5; int b=++a;` → `a=6, b=6` |
| `a++` | 후위 증가: 먼저 사용, 그 다음 +1 | `int a=5; int b=a++;` → `a=6, b=5` |
| `--a` | 전위 감소 | `int a=5; int b=--a;` → `a=4, b=4` |
| `a--` | 후위 감소 | `int a=5; int b=a--;` → `a=4, b=5` |

---

### 13-3. 비교(관계) 연산자 — 결과: `boolean`

| 연산자 | 의미 | 예시 | 결과 |
|--------|------|------|------|
| `==` | 같음 | `5 == 5` | `true` |
| `!=` | 다름 | `5 != 3` | `true` |
| `>` | 크다 | `5 > 3` | `true` |
| `<` | 작다 | `5 < 3` | `false` |
| `>=` | 크거나 같다 | `5 >= 5` | `true` |
| `<=` | 작거나 같다 | `3 <= 5` | `true` |

---

### 13-4. 논리 연산자 — 결과: `boolean`

| 연산자 | 의미 | 예시 | 설명 |
|--------|------|------|------|
| `&&` | AND (논리곱) | `true && false` → `false` | 둘 다 `true`여야 `true` |
| `\|\|` | OR (논리합) | `true \|\| false` → `true` | 하나라도 `true`면 `true` |
| `!` | NOT (부정) | `!true` → `false` | 반전 |

> **단락 평가(Short-circuit evaluation)**: `&&`는 앞이 `false`면 뒤를 평가하지 않음, `||`는 앞이 `true`면 뒤를 평가하지 않음

---

### 13-5. 대입(치환) 연산자

| 연산자 | 의미 | 예시 | 동일 표현 |
|--------|------|------|-----------|
| `=` | 대입 | `a = 5` | — |
| `+=` | 더한 후 대입 | `a += 3` | `a = a + 3` |
| `-=` | 뺀 후 대입 | `a -= 3` | `a = a - 3` |
| `*=` | 곱한 후 대입 | `a *= 3` | `a = a * 3` |
| `/=` | 나눈 후 대입 | `a /= 3` | `a = a / 3` |
| `%=` | 나머지 후 대입 | `a %= 3` | `a = a % 3` |

---

### 13-6. 비트 연산자

| 연산자 | 의미 | 예시 (`a=5(0101)`, `b=3(0011)`) | 결과 |
|--------|------|---------------------------------|------|
| `&` | 비트 AND | `5 & 3` | `1` (0001) |
| `\|` | 비트 OR | `5 \| 3` | `7` (0111) |
| `^` | 비트 XOR | `5 ^ 3` | `6` (0110) |
| `~` | 비트 NOT (반전) | `~5` | `-6` |
| `<<` | 왼쪽 시프트 | `5 << 1` | `10` (×2 효과) |
| `>>` | 오른쪽 시프트 (부호 유지) | `5 >> 1` | `2` (÷2 효과) |
| `>>>` | 오른쪽 시프트 (부호 무시, 0 채움) | `-1 >>> 1` | `2147483647` |

---

### 13-7. 삼항 연산자 (조건 연산자)
```java
조건식 ? 참일 때 값 : 거짓일 때 값
```
```java
int a = 10, b = 20;
int max = (a > b) ? a : b;  // max = 20
```

---

### 13-8. instanceof 연산자
```java
객체 instanceof 클래스명
```

- 객체가 해당 클래스의 인스턴스인지 확인 → `boolean` 반환
```java
String s = "Hello";
System.out.println(s instanceof String);  // true
```

---

### 13-9. 연산자 우선순위

| 우선순위 (높음 → 낮음) | 연산자 |
|------------------------|--------|
| 1 | `()`, `[]`, `.` |
| 2 | `++`, `--`, `!`, `~`, `+`, `-` (단항) |
| 3 | `*`, `/`, `%` |
| 4 | `+`, `-` |
| 5 | `<<`, `>>`, `>>>` |
| 6 | `<`, `<=`, `>`, `>=`, `instanceof` |
| 7 | `==`, `!=` |
| 8 | `&` |
| 9 | `^` |
| 10 | `\|` |
| 11 | `&&` |
| 12 | `\|\|` |
| 13 | `? :` (삼항) |
| 14 | `=`, `+=`, `-=` 등 대입 |

---

## 14. 코드 분석

### 14-1. Foo.java

```java
package week04;

public class Foo {
  public static int sum(int n, int m) {
    return n + m;
  }

  public static void main(String[] args) {
    int i = 20;
    int s;
    char a;

    s = sum(i, 10);
    a = '?';
    System.out.println(a);
    System.out.println("Hello");
    System.out.println(s);
  }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `int i = 20` | 정수 변수 `i`에 20 저장 |
| `s = sum(i, 10)` | `sum(20, 10)` 호출 → `20 + 10 = 30` → `s = 30` |
| `a = '?'` | 문자 변수 `a`에 `'?'` 저장 |
| `println(a)` | 문자 `'?'` 출력 |
| `println("Hello")` | 문자열 출력 |
| `println(s)` | 정수 30 출력 |

**실행 결과**
```
?
Hello
30
```

---

### 14-2. bar.java

```java
package week04;

public class bar {
  public static void main(String[] args) {
    final double PI = 3.14;
    double radius = 10.2;
    double circleArea = radius * radius * PI;

    System.out.print("반지름 " + radius + ", ");
    System.out.println("원의 면적");
    System.out.println("원의 면적 = " + circleArea);
  }
}
```

**분석**

| 단계 | 내용 |
|------|------|
| `final double PI = 3.14` | 원주율을 **상수**로 선언. `final`이므로 이후 변경 불가 |
| `double radius = 10.2` | 반지름 변수 선언 |
| `circleArea = 10.2 * 10.2 * 3.14` | 원의 넓이 = 반지름² × π = `104.04 * 3.14 = 326.6856` |
| `print(...)` | 줄 바꿈 없이 출력 |
| `println(...)` | 줄 바꿈 포함 출력 |

**실행 결과**
```
반지름 10.2, 원의 면적
원의 면적 = 326.6856
```

---

### 14-3. Woo.java (타입 변환)

```java
package week04;

public class Woo {
  public static void main(String[] args) {
    byte b = 127;
    int i = 100;

    System.out.println(b + i);
    System.out.println(10 / 4);
    System.out.println(10.0 / 4);
    System.out.println((char) 0x12340041);
    System.out.println((byte) (b + i));
    System.out.println((int) 2.9 + 1.8);
    System.out.println((int) (2.9 + 1.8));
    System.out.println((int) 2.9 + (int) 1.8);
  }
}
```

**분석 및 실행 결과**

| 코드 | 분석 | 결과 |
|------|------|------|
| `b + i` | `byte(127) + int(100)` → `b`가 `int`로 자동 변환 → `227` | `227` |
| `10 / 4` | 정수 나눗셈 → 몫만 반환 | `2` |
| `10.0 / 4` | `4`가 `4.0`으로 자동 변환 → 실수 나눗셈 | `2.5` |
| `(char) 0x12340041` | 하위 2바이트 `0x0041` = 65 = `'A'`로 강제 변환 | `A` |
| `(byte)(b + i)` | `227`을 `byte`로 강제 변환 → `227 - 256 = -29` (오버플로우) | `-29` |
| `(int) 2.9 + 1.8` | `(int)2.9 = 2` → `2 + 1.8` → `int + double` → `3.8` | `3.8` |
| `(int)(2.9 + 1.8)` | `2.9 + 1.8 = 4.7` → `(int)4.7 = 4` | `4` |
| `(int)2.9 + (int)1.8` | `2 + 1 = 3` | `3` |

**실행 결과**
```
227
2
2.5
A
-29
3.8
4
3
```

---

### 14-4. Boo.java (Scanner)

```java
package week04;

import java.util.Scanner;

public class Boo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String name = scanner.next();
    System.out.println("당신의 이름은 " + name + "입니다.");

    String city = scanner.next();
    System.out.println("당신이 사는 도시는 " + city + "입니다.");

    int age = scanner.nextInt();
    System.out.println("당신의 나이는 " + age + "살입니다.");

    double weight = scanner.nextDouble();
    System.out.println("당신의 체중은 " + weight + "kg입니다.");

    boolean single = scanner.nextBoolean();
    System.out.println("당신은 독신 여부는 " + single + "입니다.");

    scanner.close();
  }
}
```

**분석**

| 메소드 | 읽는 타입 | 설명 |
|--------|-----------|------|
| `scanner.next()` | `String` | 공백 기준 다음 토큰 하나 읽음 |
| `scanner.nextInt()` | `int` | 정수 읽음 |
| `scanner.nextDouble()` | `double` | 실수 읽음 |
| `scanner.nextBoolean()` | `boolean` | `true`/`false` 읽음 |
| `scanner.close()` | — | 스트림 자원 해제 (사용 후 반드시 닫기) |

> `next()`는 **공백 포함 문자열을 읽지 못함**. 공백 포함 한 줄 전체를 읽으려면 `nextLine()` 사용

**실행 결과** (입력: `이동주 서울 22 70.5 false`)
```
당신의 이름은 이동주입니다.
당신이 사는 도시는 서울입니다.
당신의 나이는 22살입니다.
당신의 체중은 70.5kg입니다.
당신은 독신 여부는 false입니다.
```

---

## 3주차 수업 (3월 18일)

## 1. 프로그래밍 언어의 종류

### 1-1. 기계어 (Machine Language)

- `0`과 `1`의 이진수로 구성된 언어
- **CPU는 기계어만 이해하고 처리할 수 있음**

---

### 1-2. 어셈블리어 (Assembly Language)

- 기계어 명령을 `ADD`, `SUB`, `MOV` 등의 **니모닉 기호(Mnemonic Symbol)** 로 일대일 대응시킨 언어
- 기계어보다 읽기 쉽지만, 여전히 하드웨어에 종속적

> 니모닉(Mnemonic): 기억하기 쉽도록 만든 상징적 약어

---

### 1-3. 고급 언어 (High-Level Language)

- 사람이 이해하기 쉽도록 설계된 언어
- 예: Pascal, BASIC, C/C++, Java, C#
- **절차 지향 언어**와 **객체 지향 언어**, **함수형 언어**로 분류됨

---

### 1-4. 언어 패러다임 비교

| 구분 | 절차 지향 | 객체 지향 | 함수형 |
|------|-----------|-----------|--------|
| 핵심 개념 | 순서·절차 | 객체·클래스 | 함수·불변성 |
| 데이터 처리 | 데이터와 함수 분리 | 데이터와 함수 캡슐화 | 데이터 변환 |
| 재사용성 | 낮음 | 높음 | 높음 |
| 대표 언어 | C, Pascal, Fortran | Java, C++, Python | Haskell, Lisp, Scala |
| 특징 | 전역 변수 다수 사용 | 상속·캡슐화·다형성 | 순수 함수·고차 함수·재귀 |

#### 절차 지향 언어 (Procedural Language)

- 프로그램을 절차·순서에 따라 실행하는 방식
- 데이터(입력)와 함수를 **분리**하여 작성
- 전역 변수 사용이 많아 코드 가독성·유지보수가 어려움
- 코드 유연성 부족, 재사용 어려움
- 예: `C`, `Pascal`, `Fortran`

#### 객체 지향 언어 (Object-Oriented Language)

- 현실의 객체를 모델링하여 프로그램을 작성하는 방식
- **상속(Inheritance), 캡슐화(Encapsulation), 다형성(Polymorphism)** 을 활용
- 유연하고 재사용 가능한 코드 작성 가능
- 예: `Java`, `C++`, `Python`

#### 함수형 언어 (Functional Language)

- **함수를 일급 객체(First-Class Object)로 취급**
- 상태 변경을 피하고 **불변성(Immutability)** 을 지향
- 함수의 조합으로 복잡한 작업 수행
- **재귀**, **고차 함수(Higher-Order Function)**, **순수 함수(Pure Function)** 개념 중시
- 병렬 처리와 높은 수준의 추상화 지원
- 예: `Python`, `Kotlin`, `Haskell`, `Lisp`, `Scala`

> 함수형 언어는 객체 지향의 특성을 그대로 가져가되 함수형 기능을 추가한 형태 (Python, Kotlin 등은 멀티 패러다임 언어)

---

## 2. 컴파일 (Compile)

| 용어 | 설명 |
|------|------|
| 소스(Source) | 프로그래밍 언어로 작성된 텍스트 파일 |
| 컴파일(Compile) | 소스 파일을 컴퓨터가 이해할 수 있는 기계어로 변환하는 과정 |

### 언어별 컴파일 과정
```
Java  : .java  →  .class (바이트코드)  →  JVM 실행
C     : .c     →  .obj   →  .exe
C++   : .cpp   →  .obj   →  .exe
```

> Java는 `.exe`가 아닌 `.class`(바이트코드)로 컴파일되며, JVM 위에서 실행됩니다.

---

## 3. Java의 탄생과 목적

### 3-1. 역사

- **1991년** 썬 마이크로시스템즈(Sun Microsystems)의 **제임스 고슬링(James Gosling)** 이 주도한 **그린 프로젝트(Green Project)** 에서 시작
- 가전 제품용 소프트웨어를 위해 개발 (초기 이름: **오크(Oak)**)
- **1995년** Java 공식 발표
- 인터넷과 웹의 발전과 함께 급격히 성장

### 3-2. 개발 목적: 플랫폼 독립성

**문제점 (기존 언어의 플랫폼 종속성)**

플랫폼 = 하드웨어 + 운영체제

플랫폼 호환성이 없었던 이유:
- CPU마다 기계어가 다름
- 운영체제마다 API가 다름
- 운영체제마다 실행 파일 형식이 다름

**해결 방향**
- 모든 플랫폼에서 호환되는 프로그래밍 언어 필요
- 메모리 사용량이 적고 다양한 플랫폼을 가지는 가전 제품에도 적용 가능하도록 설계

---

## 4. WORA & 바이트코드

### 4-1. WORA (Write Once, Run Anywhere)

> **"한번 작성된 코드는 OS·H/W에 상관없이 모든 플랫폼에서 실행된다"**

- C/C++ 등 기존 언어가 가진 **플랫폼 종속성 극복**
- 네트워크에 연결된 어느 클라이언트에서도 실행 가능
- 웹 브라우저, 분산 환경 지원

### 4-2. 바이트코드 (Bytecode)

- 자바 소스를 컴파일한 **목적 코드** (`.class` 파일)
- **CPU에 종속적이지 않은 중립적인 코드**
- JVM에 의해 해석되고 실행됨
- CPU가 직접 실행하지 않음 → JVM이 인터프리터 방식으로 해석

### 4-3. JVM (Java Virtual Machine)
```
[ Java Source (.java) ]
        ↓ javac (컴파일러)
[ Byte Code (.class) ]
        ↓ JVM (각 플랫폼별)
[ 실행 결과 ]
```

- **JVM 자체는 플랫폼에 종속적** (각 OS에 맞는 JVM이 따로 존재)
- 그러나 JVM이 설치된 곳이라면 **동일한 바이트코드(.class)가 그대로 실행**됨
- 오라클(Oracle) 외 IBM, MS 등 다양한 회사에서 제작·공급
- **실행 환경 = JVM + Java API (클래스 라이브러리)**

---

## 5. JDK 주요 개발 도구

| 도구 | 설명 |
|------|------|
| `javac` | 자바 소스(.java)를 바이트코드(.class)로 변환하는 **컴파일러** |
| `java` | 자바 응용프로그램 실행기 (JVM을 작동시켜 프로그램 실행) |
| `javadoc` | 자바 소스로부터 HTML 형식의 API 도큐먼트 생성 |
| `jar` | 자바 클래스들(패키지 포함)을 압축한 자바 아카이브 파일(.jar) 생성·관리 |
| `jmod` | 자바 모듈 파일(.jmod)을 만들거나 내용 출력 |
| `jlink` | 응용프로그램에 맞춘 맞춤형(Custom) JRE 제공 |
| `jdb` | 자바 응용프로그램 실행 중 오류를 찾는 **디버거** |
| `javap` | 클래스 파일의 바이트코드를 소스와 함께 보여주는 **디어셈블러** |

### javac 기본 사용법
```bash
# 기본
javac 파일명.java

# 다른 폴더에 있는 경우
javac ./src/파일명.java
```

---

## 6. Java 플랫폼 에디션

| 에디션 | 대상 | 주요 내용 |
|--------|------|-----------|
| **Java SE** (Standard Edition) | 데스크톱·서버·임베디드 | `java.lang`, `java.util`, `java.awt` 등 표준 패키지 포함 |
| **Java EE** (Enterprise Edition) | 서버 사이드 개발 | JSP, Servlet, JDBC, EJB 등 웹 프로그래밍 기능 포함 (SE 기반) |
| **Java ME** (Micro Edition) | 임베디드 (휴대폰, PDA, 셋톱박스) | SE 기반으로 소형 기기에 최적화 |

---

## 7. Java 버전 역사

### 버전 표기 변천

- 초기: `1.x` 방식 (예: Java 1.2)
- Java 1.2 이후: `J2SE` (Java 2 Standard Edition)
- Java 1.6 이후: `Java SE 6` 형태로 변경
- 내부 버전 표기는 `1.x.x` 형태로 유지

### LTS (Long-Term Support) 버전

| 버전 | 출시 연도 |
|------|-----------|
| Java 8 (LTS) | 2014 |
| Java 11 (LTS) | 2018 |
| Java 17 (LTS) | 2021 |
| Java 21 (LTS) | 2023 |

> LTS 버전은 장기간 지원이 보장되어 업계에서 안정적으로 앱을 개발하도록 하는 버전. Java 11부터는 3년 간격으로 출시.

---

## 8. Java 9 모듈화 (Modularity)

- **2017년 9월 21일** Java 9에서 도입된 새로운 기능
- **모듈(Module)**: 자바 패키지들과 이미지, XML 파일 등의 자원들을 묶은 단위
- **모듈 프로그래밍**: 레고처럼 필요한 모듈을 연결하는 방식으로 프로그램 작성
- Java 9부터 SE의 모든 클래스들을 모듈로 재구성
- JDK 설치 디렉터리 밑의 `jmods` 디렉터리에서 확인 가능

**모듈화의 장점**
- 실행 시 사용되는 Java API 클래스들을 모듈로 분할
- 필요 없는 모듈 배제 → 작은 크기의 실행 환경 구성
- **하드웨어가 열악한 소형 IoT 장치 지원 가능**

---

## 9. Java API와 패키지

### Java API

- JDK에 포함된 **클래스 라이브러리**
- 개발자는 API를 이용하여 쉽고 빠르게 자바 프로그램 개발 가능

### 패키지 (Package)

- 서로 관련된 클래스들을 계층 구조로 묶어 놓은 것 (폴더 개념)
- 클래스 이름에 패키지 이름도 포함
  - 예) `java.lang.System` → `java\lang` 디렉터리의 `System.class`
- 다른 패키지에 동일한 이름의 클래스 존재 가능
- 필요한 클래스가 속한 패키지만 `import`하여 사용
- 사용자 정의 패키지 생성 가능

---

## 10. Java의 주요 특징

### 플랫폼 독립성

- 하드웨어·운영체제에 종속되지 않는 바이트코드로 컴파일
- WORA 실현

### 객체지향 (OOP)

- **캡슐화(Encapsulation), 상속(Inheritance), 다형성(Polymorphism)** 지원
- 자바의 모든 변수와 함수는 **클래스 내에 선언**
- 내부 클래스(Inner Class) 작성 가능

### 소스와 클래스 파일

- 하나의 소스 파일에 여러 클래스 작성 가능 (단, `public` 클래스는 하나만)
- 소스 파일 이름 = `public`으로 선언된 클래스 이름 (반드시 동일해야 함)
- 클래스 파일 하나에는 클래스 하나만 존재
  - 여러 클래스를 가진 소스를 컴파일하면 클래스마다 별도의 `.class` 파일 생성

### 실행 코드 배포

- 한 개 또는 다수의 `.class` 파일로 구성
- 여러 폴더에 걸친 경우 → `jar` 압축 파일로 배포
- 응용프로그램 실행은 **`main()` 메소드에서 시작**
- 하나의 클래스 파일에 두 개 이상의 `main()` 메소드 불가

### 멀티스레드 (Multi-Thread)

- 여러 스레드의 동시 수행 환경 지원
- **운영체제 도움 없이 자체적으로 멀티스레드 지원**
- C/C++은 멀티스레드를 위해 운영체제 API를 호출해야 함

### 가비지 컬렉션 (Garbage Collection)

- 자바 언어에는 메모리 할당 기능은 있으나 메모리 반환 기능은 없음
- **사용하지 않는 메모리는 JVM이 자동으로 반환 (GC)**
- 개발자가 직접 메모리를 해제하지 않아도 됨 (C/C++과의 차이점)

---

## 11. 프로그래밍 언어 계보

| 언어 | 등장 연도 | 영향 받은 언어 |
|------|-----------|----------------|
| Assembly | - | - |
| Fortran | 1954 | Assembly |
| Algol | 1958 | Fortran |
| C | 1972 | Assembly |
| C++ | 1983 | C |
| **Java** | **1995** | **C++** |
| JavaScript | 1995 | C, Java |
| C# | 2000 | Java, C++ |
| Java 8 (LTS) | 2014 | - |
| Java 11 (LTS) | 2018 | - |
| Java 17 (LTS) | 2021 | - |
| Java 21 (LTS) | 2023 | - |

---

## 2주차 수업 (3월 11일)

## Git 주요 명령어 정리

### 초기 설정

#### git init
- 현재 폴더를 Git 저장소로 초기화함

#### git clone `<주소>`
- 원격 저장소를 복제하여 로컬에 다운로드함

---

### 상태 확인

#### git status
- 현재 파일 상태 확인 (추적 여부, 변경 여부 등)

#### git log
- 커밋 히스토리 확인

---

### 파일 추가 및 커밋

#### git add `<파일명>`
- 특정 파일을 스테이징 영역에 추가

#### git add .
- 현재 폴더의 모든 변경 파일을 추가

#### git commit -m "메시지"
- 스테이징된 파일들을 하나의 버전으로 저장

---

### 원격 저장소 작업

#### git remote add origin `<주소>`
- 원격 저장소 연결

#### git push origin `<브랜치명>`
- 로컬 커밋을 원격 저장소에 업로드

#### git pull origin `<브랜치명>`
- 원격 저장소 변경사항을 내려받고 병합

---

### 브랜치 관리

#### git branch
- 브랜치 목록 확인

#### git branch `<브랜치명>`
- 새로운 브랜치 생성

#### git checkout `<브랜치명>`
- 해당 브랜치로 이동

#### git checkout -b `<브랜치명>`
- 브랜치 생성 + 이동 동시에 수행

---

### 병합

#### git merge `<브랜치명>`
- 현재 브랜치에 다른 브랜치 내용을 합침

---

### 기타 유용한 명령어

#### git diff
- 변경된 내용 상세 비교

#### git reset --hard HEAD
- 마지막 커밋 상태로 완전히 되돌림 (주의)

#### git stash
- 현재 작업 임시 저장

#### git stash pop
- 임시 저장한 작업 다시 가져오기

---

## 마크다운 (Markdown)

> 추후 정리 예정

---

## Java 기본 코드 예제
```java
public class Main {
  public static void main(String[] args) {
    System.out.printf("Hello and welcome!");

    for (int i = 1; i <= 5; i++) {
      System.out.println("i = " + i);
    }
  }
}
```

---

## 1주차 수업

> 내용 없음