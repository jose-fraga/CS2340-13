# JUnit Testing

### Sources to learn more about JUnit
* [JUnit Guide](https://www.vogella.com/tutorials/JUnit4/article.html)
* [OReilley JUnit Primer](https://learning.oreilly.com/library/view/effective-unit-testing/9781935182573/OEBPS/Text/kindle_split_021_split_000.html)

### What is JUnit
JUnit is a framework for writing and running unit tests. 
It provides **@annotations** that the framework uses to identify test methods, test classes, and test suites. 
It also provides **assertions()** which are the main way to test code results against expected values.

In other words, assertions are methods that represent simple conditions to check for during a test to verify that the code is running as expected.
You may have multiple assertions in one test method.
If any assertion in a test fails to be true, then the test method it is under will fail.

The following are the most commonly used assertions:

* **assertEquals**— Asserts that two objects (or primitives) are equal.
* **assertArrayEquals**— Asserts that two arrays have the same items.
* **assertTrue**— Asserts that a statement is true.
* **assertFalse**— Asserts that a statement is false.
* **assertNull**— Asserts that an object reference is null.
* **assertNotNull**— Asserts that an object reference is not null.
* **assertSame**— Asserts that two object references point to the same instance.
* **assertNotSame**— Asserts that two object references do not point to the same instance.
* **assertThat**— Asserts that an object matches the given conditions (see section A.2.2 for a more thorough explanation).

The very simple test may look like this:
``` 
@Test // Annotation that tells JUnit that this is a test method
public void testAdd() { // Descriptive name for what is being tested
    int result = 1 + 2;
    assertEquals(3, result); // tests that the result equals the expected value
}
```

## How to create a new test in IntelliJ:
1. Open the file for the class you want to test
2. Click on the class name and press `Alt+Enter` to open the context menu
3. Select `Create Test`
4. Select `JUnit5` as the testing library
5. Select the methods you want to test
6. Click `OK`
7. IntelliJ will create a new test class for you with the selected methods
8. Add your test cases to the test class

## How to run tests in IntelliJ:
### Running all tests 
Right-click the **test** folder and select `Run 'All Tests'`
### Running a single test class
Right-click the specific test class (eg. TestClassName) under the test folder and select `Run 'TestClassName'`