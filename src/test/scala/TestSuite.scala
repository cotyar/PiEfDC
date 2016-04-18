import FruitCheckout.{Apple, Orange}
import org.scalatest.{FunSuite, PrivateMethodTester}

/**
  * Created by Yuriy Habarov on 18/04/2016.
  */
class TestSuite extends FunSuite with PrivateMethodTester {

  test("Apple negative price") {
    intercept[IllegalArgumentException] {
      new Apple(-1)
    }
  }

  test("Apple zero price") {
    intercept[IllegalArgumentException] {
      new Apple(0)
    }
  }

  test("Orange negative price") {
    intercept[IllegalArgumentException] {
      new Orange(-1)
    }
  }

  test("Orange zero price") {
    intercept[IllegalArgumentException] {
      new Orange(0)
    }
  }

  test("Checkout empty basket") {
    val fruits = Nil

    assert(FruitCheckout.checkout(fruits) == 0)
  }

  test("Checkout 3 Apples and 5 Oranges") {
    val fruits = Apple :: Apple :: Apple :: Orange :: Orange :: Orange :: Orange :: Orange :: Nil

    assert(FruitCheckout.checkout(fruits) == 2.20)
  }

  test("Checkout 4 Apples and 3 Oranges mixed") {
    val fruits = Orange :: Apple :: Orange :: Apple :: Apple :: Orange :: Apple :: Nil

    assert(FruitCheckout.checkout(fruits) == 1.70)
  }

  test("Checkout by names 2 Apples and 2 Oranges") {
    val decorateToStringValue = PrivateMethodTester.PrivateMethod[BigDecimal]('decorateToStringValue)
    val fruits = "Apple" :: "oRaNgE" :: "orange" :: "APPLE" :: Nil

    assert(FruitCheckout.checkoutByNames(fruits) == 1.10)
  }

  test("Plain checkout empty basket") {
    val decorateToStringValue = PrivateMethod[BigDecimal]('plainCheckout)

    val fruits = Nil

    assert((FruitCheckout invokePrivate decorateToStringValue(fruits)) == 0)
  }

  test("Plain checkout 3 Apples and 5 Oranges") {
    val decorateToStringValue = PrivateMethod[BigDecimal]('plainCheckout)

    val fruits = Apple :: Apple :: Apple :: Orange :: Orange :: Orange :: Orange :: Orange :: Nil

    assert((FruitCheckout invokePrivate decorateToStringValue(fruits)) == 3.05)
  }

  test("Plain checkout 4 Apples and 3 Oranges mixed") {
    val decorateToStringValue = PrivateMethod[BigDecimal]('plainCheckout)

    val fruits = Orange :: Apple :: Orange :: Apple :: Apple :: Orange :: Apple :: Nil

    assert((FruitCheckout invokePrivate decorateToStringValue(fruits)) == 3.15)
  }
}