# Artemis

An artemis server consists of a single broker which contains several addresses that can be anycast or multicast.

If we send a message to an anycast address then that message will be placed on every queue inside that address. This can be used as a point-to-point message system where messages are delivered to the target queues. Usually we only have one queue inside an anycast address.

The name of an anycast address can be the same as the queue name. Messages can also be sent to qualified queue names, but usually addresses are used.

Multiple consumers can consume messages from a single queue, since we can configure the queue so that messages are not deleted after it has been read. But it is easier with a one-to-one relationship.

If we send a message to a multicast address with a given topic then all consumers that listen on that topic will receive that message. For every consumer a queue will be dynamically created with the name ConsumerName.TopicName.

Queues can also have filters to prevent certain messages going to certain consumers.

The API supports several protocols: JMS, AMQP (advanced message queuing protocol), CORE (Artemis specific, like JMS but with more features).