package be.fedasil.matchit.frontend.component.showplace;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;

/**
 * Created by tmaes on 12/05/2015.
 */
public class LazyTabSheet extends TabSheet {

    public LazyTabSheet() {
        addSelectedTabChangeListener(new LazyTabChangeListener());
    }


    public static abstract class LazyTab extends CustomComponent {
        public LazyTab() {
            this(false);
        }


        public LazyTab(boolean eager) {
            if (eager) {
                refresh();
            }
        }

        public abstract Component build();

        public final void refresh() {
            setCompositionRoot(build());
        }
    }

    private static class LazyTabChangeListener implements SelectedTabChangeListener {
        @Override
        public void selectedTabChange(SelectedTabChangeEvent event) {
            Component selectedTab = event.getTabSheet().getSelectedTab();
            if (selectedTab instanceof LazyTab) {
                ((LazyTab) selectedTab).refresh();
            }
        }
    }
}