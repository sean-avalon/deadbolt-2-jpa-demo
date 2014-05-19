package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.AuthorisedUser;
import play.libs.F;
import play.mvc.Http;
import play.mvc.SimpleResult;
import views.html.accessFailed;

public class MyDeadboltHandler extends AbstractDeadboltHandler
{
    public F.Promise<SimpleResult> beforeAuthCheck(Http.Context context)
    {
        return F.Promise.pure(null);
    }

    public Subject getSubject(Http.Context context)
    {
        return AuthorisedUser.findByUserName("steve");
    }

    @Override
    public F.Promise<SimpleResult> onAuthFailure(Http.Context context,
                                                 String content)
    {
        return F.Promise.promise(new F.Function0<SimpleResult>()
        {
            @Override
            public SimpleResult apply() throws Throwable {
                return ok(accessFailed.render());
            }
        });
    }
}
