require: dict/pizza_size.csv
  name = pizza_size

require: dict/pizza_type.csv
  name = pizza_type

require: dict/quantity.csv
  name = quantity


patterns:

  $pizza_size = $entity<pizza_size>

  $pizza_type = $entity<pizza_type>

  $quantity = $entity<quantity>


