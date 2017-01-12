package com.hulaVenueBiz.ui.mine.contract;

import com.hulaVenueBiz.base.mvp.BaseMView;
import com.hulaVenueBiz.base.mvp.BasePresenter;

/**
 * @desc:         实现公告编辑逻辑
 * @author:       Leo
 * @date:         2017/1/12
 */
public interface NoticeSettingContract
{
    interface View extends BaseMView {
        void changeSuccess();
    }

    abstract class Presenter extends BasePresenter<NoticeSettingContract.View>
    {
        /**
         * 公告编辑
         * @param noticeContent     公告内容
         */
        public abstract void goToChangeNotice(String noticeContent);
    }
}
