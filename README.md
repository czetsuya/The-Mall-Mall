The Mall Mall (Mall experience x2)
=========

On the Spot Deal Finder Mobile Application

My group The Mall Mall
is developing a deal finder mobile application
to help regular shoppers on the mall
find the most interesting deals on the spot
and use that deal right on.

It will work like a GPS system with 2 components: a mini-satellite and a GPS receiver (can be any blue-tooth mobile device). The signal will be constrained on a small place like a mall, but will produce more accurate position. 

List of Possible Functions
----
#####Feeds
* deals, can be shared via social network (facebook, twitter)
* coupons
* restaurant menu
* personalized micro-location notification
* forward best feeds to friends to social networks

#####Archive
* deals taken
* transaction history

#####Vicinity (other point of interest)
* directory
* indoor navigation

#####Misc
* subscribe to store
* Online Payment
* payment transaction, paypal with double validation
* paypal (credit card)

#####Gallery Tour

Version
----

1.0

Tech
-----------
The Mall Mall uses the following technologies:

* JavaEE6
* Wildfly8
* JPA2
* iOS7
* JavaPNS - https://code.google.com/p/javapns/downloads/list
* http://www.bouncycastle.org/latest_releases.html

Installation
--------------

```sh
git clone [git-repo-url] the-mall-mall
cd the-mall-mall
mvn install
```
* Deploy on Wildfly8

--JavaPNS--
* Install JavaPNS on local git repo
```sh
mvn install:install-file -DgroupId=JavaPNS -DartifactId=JavaPNS_ -Dversion=2.2 -Dpackaging=jar -Dfile=/home/carlo/Documents/themallmall/JavaPNS_2.2.jar
```
[czetsuya]:http://about.me/czetsuya