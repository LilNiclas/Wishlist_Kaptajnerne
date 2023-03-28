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
![ERmodel](https://scontent.xx.fbcdn.net/v/t1.15752-9/337329482_1750843222036273_3032261618498114028_n.png?stp=dst-png_s960x960&_nc_cat=103&ccb=1-7&_nc_sid=aee45a&_nc_ohc=hHRNN1yej0oAX_e1nwo&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdTN0MwL5rFZQIt92JFjQ1JOjMidGLpfSifSYJa5dLsjxg&oe=644A9123)



<h2>Relational diagram</h2>
