theme: /

    state: ask_pizza
        a:  Какую пиццу желаете?
            У нас есть:
            - Гавайская
            - Маргарита
            - Пепперони
            - 4 сыра
            - Вегетарианская
        go!: /wait_pizza
    @IntentGroup
        {
          "boundsTo" : "/ask_pizza",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : true
        }

    state: wait_pizza
        state: 1
            intent!: /wait_pizza/1
            go!: /ask_size

        state: Fallback
            event: noMatch
            a: К сожалению, такой пиццы у нас нет. Пожалуйста, выберите из списка.
            go!: /ask_pizza
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/wait_pizza",
                name: "wait_pizza buttons",
                handler: function($context) {
                }
            });

    state: ask_size
        a:  Отлично, вы выбрали пиццу: {{$session.pizza}}.
            Какого размера пиццу вы хотите?
        go!: /wait_size
    @IntentGroup
        {
          "boundsTo" : "/ask_size",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }

    state: wait_size
        state: 1
            intent: /wait_size/1
            go!: /ask_quantity

        state: Fallback
            event: noMatch
            a: К сожалению, такого размера у нас нет. Пожалуйста, выберите: маленькая, средняя или большая.
            go!: /ask_pizza
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/wait_size",
                name: "wait_size buttons",
                handler: function($context) {
                }
            });

    state: ask_quantity
        a: Сколько пицц {{$session.pizza}} размера {{$session.size}} вам нужно?
        go!: /wait_quantity
    @IntentGroup
        {
          "boundsTo" : "/ask_quantity",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }

    state: wait_quantity
        state: 1
            intent: /wait_quantity/1
            go!: /confirm_order

        state: Fallback
            event: noMatch
            a: Пожалуйста, укажите количество цифрами (от 1 до 10).
            go!: /ask_pizza
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/wait_quantity",
                name: "wait_quantity buttons",
                handler: function($context) {
                }
            });

    state: confirm_order
        a: Отлично, вам нужно: {{$session.quantity}} {{$session.pizza}} размером {{$session.size}}. Верно?
        go!: /wait_confirmation
    @IntentGroup
        {
          "boundsTo" : "/confirm_order",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }

    state: wait_confirmation
        state: 1
            intent: /sys/aimylogic/ru/agreement
            go!: /order_complete

        state: 2
            intent: /sys/aimylogic/ru/negation
            go!: /ask_pizza
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/wait_confirmation",
                name: "wait_confirmation buttons",
                handler: function($context) {
                }
            });

    state: order_complete
        a: Пицца скоро приедет!