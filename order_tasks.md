#tasks ()
2. product creation
	2. should return 201 when create a product  (resource.post)  --10 :: 6
	3. should contain creation uri in header location (resource.post) --10 :: 3
	4. should 400 when the product param is not complete --10 :: 6
	4. --------------------------------------------------
	3. should have tried to save the creation data into database and should able to get that product after creation. (repo.save, repo.findById) --10 :: 7
	4. should that one product's id is the same as the created one (resourc.post, repo.save, mapper, database, records; repo.findById, mapper) --15 :: 12
	4. should the creation uri contains product id in header location -- 5 :: 2
6. get some product
	6. should return 200 when get some product (resource.get) --10 :: 5
	7. should the response body contains right uri, name, description, price info (resource.get) --15 :: 6
	3. --------------------------------------------------
	4. should return 404 when no products in db --5 :: 1
6. get all products
	1. should return 200 when get products (resource.get) --10 :: 2
	3. --------------------------------------------------
	2. should have tried to search from database and should able to get a least one product if database is not empty (repo.findAll) --5 :: 3
	3. should that one product's id are the same as expected (mapper-findAll) --10 :: 3
	3. --------------------------------------------------
	2. should the response body contains at least  one item info (resource.get, record) --10 ::
	2. should the response body contains right uri, name, description, price info (resource.get, record) --5 :: 3
	4. should return empty when no products in db --5 :: 1
11. user register
	11. should return 201 when register a new user (resource.post) --10 :: 4
	12. should return 400 when the registered name is not composed of letters and numbers, at least one (resource.post) -- 10 :: 9
	13. should include creation uri in header location (resource.post) --10 :: 5
	3. --------------------------------------------------
	14. should have tried to save the user info into database and should able to get a user after registered (repo.findById) --10 :: 6
	16. should the user's id is the same as the saved one. (resourc.post, repo.save, mapper, database, record; repo.findById, mapper) --15 :: 6
	3. -------------------------------------------------
	4. should the creation uri contains user id in header location -- 5 :: 2
11. get one user
    1. should 200 when get some user --5 :: 5
    1. should contains right uri, name, id info when get user --15 :: 5
    1. should 404 when user doesn't exist --5 :: 1
18. order creation
	18. should return 201 when creating an order (resource.post) --15 :: 7
	19. should include the creation uri in header location (resource.post) --2 :: 3
	3. --------------------------------------------------
	20. should have tried to save the order info into database and should able to get the order after created order (repo.findById) --15 :: 6
	22. should the order's id is the same as the created one (resourc.post, repo.save, mapper, database, record; repo.findById, mapper) --13 :: 10
	3. --------------------------------------------------
	4. should the creation uri contains order id in header location -- 5 :: 1
	2. should return 400 when the input doesn't contain name, address, phone --10 :: 4
	4. should return 400 when the order contains 0 order item or the item doesn't exist, or the item's product_id, quantity is null --10 :: 4
27. get some order of some user
	28. should return 200 when review some order of some user (resource.get) --15 :: 6
	29. should the response body contain right uri info (resource.get) --15 :: 8
 	20. should the response body contain right name, address, phone,  created\_at info (resource.get, record) --10 :: 4
 	21. should the response body contain at least one order_item info (resource.get, record) --15 :: 18
 	22. should the order\_item contain right product_id, quantity, amount info (resource.get, record) --10 :: 5
 	1. 20. should the response body contain right total\_price info (resource.get, record) --5 :: 3
	4. should return 404 when no order in db --5 :: 1
27. get all orders of some user
	28. should return 200 when review all orders of some user (resource.get) --5 :: 2
	3. --------------------------------------------------
	29. should have tried to fetch all orders from database and should get one order when there's one order in database (repo.findAll) --2 :: 6
	31. should the order's id is the same as the created one (resourc.post, repo.save, mapper, database, record; repo.findAll, mapper) --13 :: 2
	3. --------------------------------------------------
	29. should the response body contain at least one order info. (resource.get, record)  --10 ::
    29. should the response body contain right uri info (resource.get) --5 ::
    20. should the response body contain right name, address, phone, total\_price info (resource.get, record) --5 :: 2
29. create payment
	30. should return 201 when pay (resource.post) --15 :: 6
	1. should 400 when input doesn't contain pay_type, amount -- 10 ::
	3. --------------------------------------------------
	31. should try to save the payment info into database and should able to get that payment after pay (resourc.post, repo.findByOrderId) --20 ::
	32. should the id of that payment is as expected (resourc.post, repo.save, mapper, database, record; repo.findByOrderId, mapper) --20 ::
34. get payment of some order
	32. should return 200 when get payment (resourc.get) --10 ::
	33. should the response body include right pay type info -- 12 ::
	35. should the response body include right amount, order_uri & payment uri (resource.get) --15 ::
	34. should the response body include right creating date (resoure.get, mapper-findByOrderId, record) --5 ::
	4. should return 404 when no payment in db --5 ::

#databases
1. product: 
	2. id
	2. name
	3. description
	4. price
2. order:
	3. 	id
	1. user_id
	3. name
	4. address
	5. phone
	7. created_at
	9. pay_state
7. order_items:
	8. order_id
	8. product_id
	9. quantity
	10. amount
10. user:
	11. id
	11. name
12. payments:
	13. order_id
	8. pay_type
	10. pay_at
	11. pay_amount


	



