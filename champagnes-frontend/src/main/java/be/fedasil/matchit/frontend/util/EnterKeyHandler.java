package be.fedasil.matchit.frontend.util;

import be.fedasil.matchit.frontend.component.lookupplace.LookupPlaceView;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 * Created by tmaes on 30/04/2015.
 */
public abstract class EnterKeyHandler {

    TextField textfield;
    LookupPlaceView.LookupViewListener listener;

   /* public EnterKeyHandler(LookupPlaceView.LookupViewListener listener, TextField textfield){
        this.listener = listener;
        this.textfield = textfield;
    }*/

    final ShortcutListener enterShortCut = new ShortcutListener(
            "EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ENTER, null) {
        @Override
        public void handleAction(Object sender, Object target) {
            onEnterKeyPressed();
        }
    };

    public void installOn(final TextField component)
    {
        component.addFocusListener(
                new FieldEvents.FocusListener() {

                    @Override
                    public void focus(FieldEvents.FocusEvent event
                    ) {
                        component.addShortcutListener(enterShortCut);
                    }

                }
        );

        component.addBlurListener(
                new FieldEvents.BlurListener() {

                    @Override
                    public void blur(FieldEvents.BlurEvent event
                    ) {
                        component.removeShortcutListener(enterShortCut);
                    }

                }
        );
    }

    public abstract void onEnterKeyPressed();
}
