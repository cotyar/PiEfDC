/**
  * Created by Yuriy Habarov on 18/04/2016.
  */


object FruitCheckout {

  abstract class Fruit (val price: BigDecimal) {
    require(price > 0)
  }

  case class Apple(override val price: BigDecimal) extends Fruit(price)
  object Apple extends Apple(BigDecimal(0.6))

  case class Orange(override val price: BigDecimal) extends Fruit(price)
  object Orange extends Orange(BigDecimal(0.25))

  private def plainCheckout (items: List[Fruit]) = {
    items.map(_.price).sum
  }

  private def discountedCheckout (items: List[Fruit]) = {
    val appleCount = items.count(_ == Apple)
    val appleSum = (appleCount - appleCount / 2) * Apple.price
    val orangeCount = items.count(_ == Orange)
    val orangeSum = (orangeCount - orangeCount / 3) * Orange.price
    appleSum + orangeSum
  }

  def checkout: List[Fruit] => BigDecimal = discountedCheckout

  def checkoutByNames (itemNames: List[String]) = {
    val items = itemNames
      .map(_.toUpperCase())
      .map(_ match {
        case "APPLE" => Apple
        case "ORANGE" => Orange
        case item => throw new IllegalArgumentException(s"Item '$item' is not 'Apple' or 'Orange'")
      })
    checkout(items)
  }

}
