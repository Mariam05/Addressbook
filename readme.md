1. navigate to http:localhost:8080/addressbook/new # this creates a new addressbook. An optional param is 'name'
2. navigate to http:localhost:8080/addressbook/buddy?name=johndoe&phone=123&id=1 // id is the id of the addressbook we created in the previous step
3. repeat step 2 as many times as you want
4. you can navigate to http:localhost:8080/addressbook/get?id={} to get all the buddies for an addressbook with the id passed.

