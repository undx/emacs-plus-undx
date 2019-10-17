package com.mulgasoft.emacsplus.actions.search;

import com.intellij.find.FindModel;
import com.intellij.find.FindSettings;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.mulgasoft.emacsplus.actions.EmacsPlusAction;
import com.mulgasoft.emacsplus.handlers.ISHandler;

public class ISCase extends EmacsPlusAction {
  public ISCase() {
    super(new ISCase.myHandler());
  }

  private static final class myHandler extends ISHandler {

    @Override
    public void executeWriteAction(Editor editor, Caret caret, DataContext dataContext) {
      Editor selected = FileEditorManager.getInstance(editor.getProject()).getSelectedTextEditor();
      FindModel findModel = ISHandler.getFindModel(selected);
      if (findModel != null) {
        boolean state = !findModel.isCaseSensitive();
        findModel.setCaseSensitive(state);
        FindSettings.getInstance().setLocalCaseSensitive(state);
      }

    }

    @Override
    protected boolean isEnabledForCaret(Editor editor, Caret caret, DataContext dataContext) {
      return isInISearch(editor);
    }
  }
}
