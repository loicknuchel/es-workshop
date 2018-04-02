package workshop.macdo.domain

case class ProductId(value: String) extends AnyVal

case class Product(id: ProductId,
                   name: String)
