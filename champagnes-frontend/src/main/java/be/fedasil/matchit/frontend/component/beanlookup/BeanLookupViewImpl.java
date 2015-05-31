package be.fedasil.matchit.frontend.component.beanlookup;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class BeanLookupViewImpl extends CustomComponent implements BeanLookupView, ClickListener {

	private static final long serialVersionUID = 1665028802309450706L;
	private BeanLookupViewListener listener;
	private TextField txtJndi=new TextField("JNDI");

	/**
	 * On click of the lookup button, pass the entered id to the listener.
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		listener.lookup(txtJndi.getValue());
	}

	@Override
	public void addListener(BeanLookupViewListener listener) {
		this.listener=listener;
	}

	public BeanLookupViewImpl()
	{
		FormLayout form=new FormLayout();
		Button btnLookup=new Button("Lookup",this);
		form.addComponents(txtJndi,btnLookup);
		setCompositionRoot(form);
	}

	@Override
	public void setMessage(String message) {
		Notification.show("Error",message,Notification.Type.WARNING_MESSAGE);
	}
}
