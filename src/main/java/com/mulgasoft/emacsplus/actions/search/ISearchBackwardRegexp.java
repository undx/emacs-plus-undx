package com.mulgasoft.emacsplus.actions.search;

import com.intellij.find.FindUtil;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.mulgasoft.emacsplus.util.EmacsIds;

public class ISearchBackwardRegexp extends ISearchBackward {

    protected static ISearch delegateAction(AnActionEvent e) {
        Editor editor = FileEditorManager.getInstance(e.getProject()).getSelectedTextEditor();
        ISearch searcher = ISearch.from(editor);
        if (searcher == null) {
            com.mulgasoft.emacsplus.util.ActionUtil.dispatch(EmacsIds.ISEARCH_ID, e.getDataContext());
            searcher = ISearch.from(editor);
        } else {
            searcher.requestFocus();
            FindUtil.configureFindModel(false, editor, searcher.getFindModel(), false);
        }

        if (searcher != null) {
            searcher.getFindModel().setRegularExpressions(true);
            searcher.searchBackward();
        }
        PluginManager.getLogger().warn("searcher backwregex " + searcher);
        return searcher;
    }

}
