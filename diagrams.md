<h2>ER diagram:</h2>

```SQL

Table Wishes {
  wish_ID int [pk]
  name varchar
  price double
  description varchar
  link varchar
}

Table Wishlist {
  wishlist_ID int [pk]
  name varchar
}

Table Wistlist_wishes {
  wishlist_ID int
  wish_ID int
}

ref: public.Wishlist.wishlist_ID - public.Wistlist_wishes.wishlist_ID
ref: public.Wishes.wish_ID < public.Wistlist_wishes.wish_ID

```
![ERModel](https://scontent.xx.fbcdn.net/v/t1.15752-9/337329482_1750843222036273_3032261618498114028_n.png?stp=dst-png_s960x960&_nc_cat=103&ccb=1-7&_nc_sid=aee45a&_nc_ohc=hHRNN1yej0oAX_e1nwo&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdTN0MwL5rFZQIt92JFjQ1JOjMidGLpfSifSYJa5dLsjxg&oe=644A9123)


<h2>Relational diagram</h2>

![RelationalModel](https://scontent-cph2-1.xx.fbcdn.net/v/t1.15752-9/338839046_168258556110125_2824207743627128412_n.png?_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=T3Mp9P9g578AX9XrQd6&_nc_ht=scontent-cph2-1.xx&oh=03_AdRSTIG5M5GWMqyLwvxA2hbN9C5NbFNe7sZjHn9JnM8wWw&oe=644A9DC4)
<sub>This is not the final version</sub>
