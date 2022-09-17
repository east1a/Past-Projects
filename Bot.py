import tweepy

class tweepy.StreamingClient(bearer_token = AAAAAAAAAAAAAAAAAAAAAF6ZbQEAAAAAHav7fG49AzdG1ToSh3kTEabeQYs%3D3bPQzsVkUa7bhaBxHeC2TYVjBqcDL2bo5VdsTjAKlp3FrZcOfy)

class StreamListener(tweepy.StreamListener):
    def on_status(self, status):
        print(status.id_str)

    def on_error(self, status_code):
        print("Encountered streaming error (", status_code, ")")
        sys.exit()


apiKey = 'IyXNY0859xdxNlQNZkPDXZrCP'
apiKeySecret = 'qWMHKxN8lfwcmulcwdNaSiXTiI0woW0fupqejRoUguUSZxUUvQ'

accessToken = '1513691830323273729-9z9o2tuTlRYDN2TvcjsmDBtbNmbfMB'
accessTokenSecret = 'LWSXci3hJ0xSLBJY56qqmFmpqzUG8j9lOUh2FMqmAjfZZ'
#
#Bearer AAAAAAAAAAAAAAAAAAAAAF6ZbQEAAAAAHav7fG49AzdG1ToSh3kTEabeQYs%3D3bPQzsVkUa7bhaBxHeC2TYVjBqcDL2bo5VdsTjAKlp3FrZcOfy
auth = tweepy.OAuth1UserHandler(apiKey, apiKeySecret)
auth.set_access_token(accessToken, accessTokenSecret)
api = tweepy.API(auth)
Client = tweepy.Client(consumer_key=apiKey, consumer_secret= apiKeySecret, access_token= accessToken, access_token_secret= accessTokenSecret)
streamListener = StreamListener()
stream = tweepy.Stream(auth = api.auth, listener = streamListener, tweet_mode = 'extended')

myTweetId = 1517944950674968577

hashtag = '#btc'
screen_name = "@whale_alert"

print(api.home_timeline)




def tweet():
    tweets = api.user_timeline(user_id = screen_name, tweet_mode = 'extended')
    for tweet in reversed(tweets) :
        if '#btc' in tweet.full_text.lower:
            print (str(tweet.full_text.lower))

