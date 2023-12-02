package ETU.WebGWT.client;

import ETU.WebGWT.shared.Musician;
import java.util.List;
import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MusicianValue implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
	private final GreetingServiceAsync myService =
            GreetingService.App.getInstance();
  /**
   * список музыкантов
   */
	final ListBox musiciansListBox = new ListBox(false);
	/** Точка входа в приложение - аналог main */
    public void onModuleLoad() {
        musiciansListBox.setFocus(true);
        refreshMusiciansList();
        
        //создание и заполнение таблицы
        final CellTable<Musician> mainTable = createCellTable();
        final ListDataProvider<Musician> mainDataProvider = new ListDataProvider<>();
        mainDataProvider.addDataDisplay(mainTable);
        RootPanel.get("PanelContainer").add(mainTable);
        myService.getMusicianList(
                new AsyncCallback<List<Musician>>() {
                    @Override
                    public void onFailure(Throwable caught) {

                    }
                    @Override
                    public void onSuccess(List<Musician> result) {
                        mainDataProvider.setList(result);
                    }
                }
        );
        
        final VerticalPanel experiencePanel = new VerticalPanel();
        experiencePanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        experiencePanel.setVisible(true);
        final Label experienceLabel = new Label("Введите максимальный стаж работы");
        final Label errorLabel = new Label("Неверно введенный стаж работы");
        final Button button = new Button("Получить список");
        final TextBox experienceField = new TextBox();
        experienceField.getElement().setPropertyString("placeholder", "Стаж работы");
        errorLabel.setVisible(false);
        experiencePanel.add(experienceLabel);
        experiencePanel.add(errorLabel);
        experiencePanel.add(experienceField);
        experiencePanel.add(button);
        
        button.addClickHandler(event -> {
            int experience;
            try{
            	experience = Integer.parseInt(experienceField.getText());
                List<Musician> tempList = new ArrayList<>(mainDataProvider.getList());
                tempList.removeIf(boy -> boy.getExp() > experience);
                mainDataProvider.setList(tempList);
                mainDataProvider.refresh();
                refreshMusiciansList();
                experienceField.setText("");
                errorLabel.setVisible(false);
            }
            catch (Exception e){
                errorLabel.setVisible(true);
            }
        });

        RootPanel.get("experienceForm").add(experiencePanel);
    }
    
    private CellTable<Musician> createCellTable(){
        final CellTable<Musician> table = new CellTable<>();
        //без этой строчки ничего не будет видно
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        TextColumn<Musician> nameColumn = new TextColumn<Musician>() {
            @Override
            public String getValue(Musician object) {
                return object.getName();
            }
        };
        table.addColumn(nameColumn, "Имя");//колонка, ее название

        TextColumn<Musician> instrumentColumn = new TextColumn<Musician>() {
            @Override
            public String getValue(Musician object) {
            	return object.getInstrument();
            }
        };
        table.addColumn(instrumentColumn, "Инструмент");
        
        TextColumn<Musician> experienceColumn = new TextColumn<Musician>() {
            @Override
            public String getValue(Musician object) {
                return String.valueOf(object.getExp());
            }
        };
        table.addColumn(experienceColumn, "Стаж работы");

        return table;
    }
    
    private void  refreshMusiciansList(){
        myService.getMusicianList(new AsyncCallback<List<Musician>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Musician> result) {
            	musiciansListBox.clear();
                for (Musician boy : result)
                	musiciansListBox.addItem(boy.getName());
            }
        });
    }
}
