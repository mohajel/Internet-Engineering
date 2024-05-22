
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
