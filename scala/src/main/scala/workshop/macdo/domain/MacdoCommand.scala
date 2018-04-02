package workshop.macdo.domain

import workshop.macdo.eventsourcing.{Command, Meta}

trait MacdoCommand extends Command
case class OrderMeal(client: ClientId, products: Seq[ProductId], meta: Meta) extends MacdoCommand

// order automate
case class StartOrder(meta: Meta) extends MacdoCommand
case class AddProduct(product: ProductId, quantity: Int, meta: Meta) extends MacdoCommand
case class ModifyQuantity(product: ProductId, quantity: Int, meta: Meta) extends MacdoCommand
case class DeleteProduct(product: ProductId, meta: Meta) extends MacdoCommand
case class AddMenu(menu: Menu, products: Seq[ProductId], meta: Meta) extends MacdoCommand
case class ValidOrder(meta: Meta) extends MacdoCommand
case class CardCheckout(meta: Meta) extends MacdoCommand
case class CoinCheckout(amount: Double, meta: Meta) extends MacdoCommand
