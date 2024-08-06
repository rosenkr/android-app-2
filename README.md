A stock market app.
Work in progress. 
Personal student portfolio project.
To run the program you will need your own MarketStack API key: in local.properties, add an entry API_KEY=<your_key>. MarketStack gives 100 free calls per month but requires signing up.

In the future, the project intends to include a server-side which fetches live
data from stock market APIs, stores historic data in a database in the cloud, and makes that data available to clients,
thus the client should not need their own key.

The idea of the app is to tap a fairly untapped and growing market by taking advantage of the recent gamblifying of the stock market which was otherwise historically seen as something serious and separable from old fashioned gambling. Since Covid and the QE that followed, young people with extra cash sitting at home found their way to leveraged stock market instruments that track underlying assets. This trading is effectively filling the role of gambling for many, under the guise of skill. 

The app aims to game-ify trading (the meaning of this term is yet to be explored in the apps context, but it should be something that is opposite of the boring flows and UI's of banking/trading apps that exist today: Avanza, Nordnet, etc. These have only recently managed to make their apps modern.) 

For example, they do not take advantange of mobile multi-touch which surely could be used to speeden up trades on the phone, instead of requiring to fill out forms which is slow on mobile. Another interesting idea is to add the possibility to trade by interacting with a graph directly. Furthermore, typical apps I have used do not implement ways to set stop losses for some regular instruments such as warrants, although you can set stop-losses for the underlying assets. This app aims to rectify such lacking functionalities. 

The main idea is that users are allotted some amount of in-app currency, but trades are against the real market. This way, the app can target a younger audience that can not yet trade for real money, but are interested in the markets, not in slot machines...

The second idea is the feature that users should be able to build strategies that can be applied on a given asset to trade it based on the strategy. So options of fully algorithmic trading, semi-assisted, or fully manual trading should be available.

Finally, users should be able to compare, compete, view their statistics, etc...

The monetization could be through ads, affiliations, subscription-based, or a freemium style by giving users the option to unlock 'more powerful tools' of the app (referring to the algorithmic trading options).
