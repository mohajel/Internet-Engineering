
# Json Format


## NO_ERROR:

        output.put("success", true);
        output.put("title", "Login");
        output.put("data", /any data big or small/);
        output.put("message", "login");
        output.put("icon", "warning/success");

## ERROR

    output.put("success", false);
    output.put("title", "Login");
    output.put("data", {"error" : "ERROR_MSG"});
    output.put("message", "login"); //not necessary


# Github Redirect 
http://localhost:8080/oauth/redirect?code=e295c1a778b6d5373242
http://localhost:8080/oauth/redirect?code=f04e105bcd0525560c01



```
{"login":"mohajel","id":102811563,"node_id":"U_kgDOBiDHqw","avatar_url":"https://avatars.githubusercontent.com/u/102811563?v=4","gravatar_id":"","url":"https://api.github.com/users/mohajel","html_url":"https://github.com/mohajel","followers_url":"https://api.github.com/users/mohajel/followers","following_url":"https://api.github.com/users/mohajel/following{/other_user}","gists_url":"https://api.github.com/users/mohajel/gists{/gist_id}","starred_url":"https://api.github.com/users/mohajel/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/mohajel/subscriptions","organizations_url":"https://api.github.com/users/mohajel/orgs","repos_url":"https://api.github.com/users/mohajel/repos","events_url":"https://api.github.com/users/mohajel/events{/privacy}","received_events_url":"https://api.github.com/users/mohajel/received_events","type":"User","site_admin":false,"name":null,"company":null,"blog":"","location":null,"email":null,"hireable":null,"bio":null,"twitter_username":null,"public_repos":17,"public_gists":0,"followers":6,"following":8,"created_at":"2022-04-01T13:48:38Z","updated_at":"2024-05-09T09:39:35Z"}
```

# JWT Registered Claim Names


4.1.  Registered Claim Names

   The following Claim Names are registered in the IANA "JSON Web Token
   Claims" registry established by Section 10.1.  None of the claims
   defined below are intended to be mandatory to use or implement in all
   cases, but rather they provide a starting point for a set of useful,
   interoperable claims.  Applications using JWTs should define which
   specific claims they use and when they are required or optional.  All
   the names are short because a core goal of JWTs is for the
   representation to be compact.

4.1.1.  "iss" (Issuer) Claim

   The "iss" (issuer) claim identifies the principal that issued the
   JWT.  The processing of this claim is generally application specific.
   The "iss" value is a case-sensitive string containing a StringOrURI
   value.  Use of this claim is OPTIONAL.

4.1.2.  "sub" (Subject) Claim

   The "sub" (subject) claim identifies the principal that is the
   subject of the JWT.  The claims in a JWT are normally statements
   about the subject.  The subject value MUST either be scoped to be
   locally unique in the context of the issuer or be globally unique.
   The processing of this claim is generally application specific.  The
   "sub" value is a case-sensitive string containing a StringOrURI
   value.  Use of this claim is OPTIONAL.

4.1.3.  "aud" (Audience) Claim

   The "aud" (audience) claim identifies the recipients that the JWT is
   intended for.  Each principal intended to process the JWT MUST
   identify itself with a value in the audience claim.  If the principal
   processing the claim does not identify itself with a value in the
   "aud" claim when this claim is present, then the JWT MUST be
   rejected.  In the general case, the "aud" value is an array of case-
   sensitive strings, each containing a StringOrURI value.  In the
   special case when the JWT has one audience, the "aud" value MAY be a
   single case-sensitive string containing a StringOrURI value.  The
   interpretation of audience values is generally application specific.
   Use of this claim is OPTIONAL.

4.1.4.  "exp" (Expiration Time) Claim

   The "exp" (expiration time) claim identifies the expiration time on
   or after which the JWT MUST NOT be accepted for processing.  The
   processing of the "exp" claim requires that the current date/time
   MUST be before the expiration date/time listed in the "exp" claim.


<!-- eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhbGkiLCJpc3MiOiJNSVpET09OSSIsInJvbGVzIjpbIk1BTkFHRVIiXSwiaWF0IjoxNzE2NTYzOTQ0LCJleHAiOjE3MTY1NzM1NDR9.L8VW-vnRHu_ek7-gRgmQL9ykNo-gxrISFJnvgG0dnbKoVo4X6hYYg6zibsTooFXONh0YOoqCqzi5L4tIC7JxW-iyF3ILlIUtlnv0pg3v52hElZiITHaxRZS0QdP7CjKWg26URFGc6jkHsmuX5nOvjw8gJGYSBRJJdKPVVXR789AcXisGro-cTUM7WNQ3lROtbEA06F7m9PcVWroE3PKtkwfvfNzh4MgqvEae3pKZrIAvrXTYOYZs6NsSauVAG1ztrtRP1tzu0KlY4TZknlRUq351kstaL2E_6DkFCg0s5YleWN_q6aoyHlG47eOCeONQBMPrHGkdg-d5x6DWvBSBTA


eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtb2hhamVsIiwiaXNzIjoiTUlaRE9PTkkiLCJyb2xlcyI6WyJDTElFTlQiXSwiaWF0IjoxNzE2NTYzNTk3LCJleHAiOjE3MTY1NzMxOTd9.xNBdgLhWj7hBqzkTT7nw0CzmwgWJ1Tz_MU1rxo3t9QniYXBs3AtdQxdzKueDB5KvouzxMmolOEd1O95hMVWLCw55HOfOGCqh44Whf8TYeqahv0lyCgWKx2AWV7fd7j7iQm4Ys-fIBXUWVVitJVlD_pH8BL9UlXDHJBzpQcAI9gghsQ08D4dTemconF9E7cEAB_gsJF7DFXSPowTzAC9Lvwhg-vooUS8W_o-7RFfXcA7IYOQ2kiv_pt_e57C08i2jKpqeenW8jttxGUEzlHuWW5KdO2FiUkKg59LtqS_yawJhC_7R4JAujgnEM-TYs0b14EYZJuLSCY0kOVJwx8pqcA -->