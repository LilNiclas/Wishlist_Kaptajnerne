<h2>ER diagram:</h2>

```SQL

Table Wishes {
  wish_ID int [pk]
  itemName varchar
  price double
  description varchar
  link varchar
}

Table Wishlist {
  wishlist_ID int [pk]
  listName varchar
  username varchar
}

Table Wistlist_wishes {
  wishlist_ID int
  wish_ID int
}

Table User {
  email varchar [pk]
  username varchar
}

ref: public.Wishlist.wishlist_ID - public.Wistlist_wishes.wishlist_ID
ref: public.Wishes.wish_ID < public.Wistlist_wishes.wish_ID
ref: public.User.username - public.Wishlist.username

```
![ERModel](https://scontent.xx.fbcdn.net/v/t1.15752-9/337743579_772980004344415_6332172548985483626_n.png?stp=dst-png_p403x403&_nc_cat=103&ccb=1-7&_nc_sid=aee45a&_nc_ohc=43sXUQ4YcREAX_cRGrc&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdRtfOktPnjatharTUTzr_kRVKXsJPA6vh6Al4tFr1g-PQ&oe=644C94A9)




<h2>Relational diagram</h2>

![RelationalModel](https://scontent-cph2-1.xx.fbcdn.net/v/t1.15752-9/338839046_168258556110125_2824207743627128412_n.png?_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=T3Mp9P9g578AX9XrQd6&_nc_ht=scontent-cph2-1.xx&oh=03_AdRSTIG5M5GWMqyLwvxA2hbN9C5NbFNe7sZjHn9JnM8wWw&oe=644A9DC4)

<sub>The relational model is not up to date</sub>
