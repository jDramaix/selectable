/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwtquery.plugins.selectable.client;

import static com.google.gwt.query.client.GQuery.$;
import static gwtquery.plugins.selectable.client.Selectable.Selectable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import gwtquery.plugins.selectable.client.SelectableOptions.Tolerance;

public class SelectableSample2 implements EntryPoint {

  public void onModuleLoad() {

    SelectableOptions o = new SelectableOptions();
    o.setFilter("tr");
    $("#selectable").as(Selectable).selectable(o);

    RootPanel.get("selectable-options").add(new SelectableOptionsPanel(o));

  }

  public static class SelectableOptionsPanel extends Composite {

    private static SelectableoptionsPanelUiBinder uiBinder = GWT
        .create(SelectableoptionsPanelUiBinder.class);

    @UiTemplate(value = "SelectableOptionsPanel.ui.xml")
    interface SelectableoptionsPanelUiBinder extends
        UiBinder<Widget, SelectableOptionsPanel> {
    }

    private SelectableOptions options;

    @UiField
    ListBox filterListBox;
    @UiField
    TextBox appendToBox;
    @UiField
    TextBox delayBox;
    @UiField
    TextBox distanceBox;
    @UiField
    CheckBox disabledCheckBox;
    @UiField
    CheckBox multiSelectCheckBox;
    @UiField
    ListBox toleranceListBox;

    public SelectableOptionsPanel(SelectableOptions o) {
      options = o;
      initWidget(uiBinder.createAndBindUi(this));
      init();

    }

    private void init() {

      filterListBox.addItem("tr");
      filterListBox.addItem("td");
      filterListBox.setSelectedIndex(0);

      appendToBox.setValue(options.getAppendTo(), false);
      
      delayBox.setValue(""+options.getDelay(), false);
      
      distanceBox.setValue(""+options.getDistance(), false);

      disabledCheckBox.setValue(options.isDisabled(), false);

      multiSelectCheckBox.setValue(options.isMultiSelect(), false);

      toleranceListBox.addItem(Tolerance.TOUCH.name());
      toleranceListBox.addItem(Tolerance.FIT.name());
      toleranceListBox.setSelectedIndex(0);

    }

    @UiHandler(value = "filterListBox")
    public void onFilterChange(ChangeEvent e) {
      options.setFilter(filterListBox
          .getValue(filterListBox.getSelectedIndex()));
    }

    @UiHandler(value = "appendToBox")
    public void onAppendToChange(ValueChangeEvent<String> e) {
      options.setAppendTo(e.getValue());
    }
    
    @UiHandler(value = "delayBox")
    public void onDelayChange(ValueChangeEvent<String> e) {
      options.setDelay(new Integer(e.getValue()));
    }
    
    @UiHandler(value = "distanceBox")
    public void onDistanceChange(ValueChangeEvent<String> e) {
      options.setDistance(new Integer(e.getValue()));
    }

    @UiHandler(value = "disabledCheckBox")
    public void onDisabledChange(ValueChangeEvent<Boolean> e) {
      options.setDisabled(e.getValue());
    }

    @UiHandler(value = "multiSelectCheckBox")
    public void onMultiSelectChange(ValueChangeEvent<Boolean> e) {
      options.setMultiSelect(e.getValue());
    }

    @UiHandler(value = "toleranceListBox")
    public void onToleranceChange(ChangeEvent e) {
      String tolerance = toleranceListBox.getValue(toleranceListBox
          .getSelectedIndex());
      options.setTolerance(Tolerance.valueOf(tolerance));
    }

  }
}

