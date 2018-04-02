package workshop.macdo.domain

import workshop.macdo.eventsourcing.{Event, Meta}

trait MacdoEvent extends Event
case class MealOrdered(client: ClientId, products: Seq[ProductId], meta: Meta) extends MacdoEvent

// order automate
case class OrderStarted(meta: Meta) extends MacdoEvent
case class ProductAdded(product: ProductId, quantity: Int, meta: Meta) extends MacdoEvent
case class QuantityModified(product: ProductId, quantity: Int, meta: Meta) extends MacdoEvent
case class ProductRemoved(product: ProductId, meta: Meta) extends MacdoEvent
case class MenuAdded(menu: Menu, products: Seq[ProductId], meta: Meta) extends MacdoEvent
