!function(e){var i=function(e,i,d){"string"==typeof d.isHidden&&(d.isHidden=a.eqs(d.isHidden,"true")),d.isHidden=!!d.isHidden,h.initHideForExCheck(e,d)},d=function(){},t=function(e,i){i.showNodes=function(i,d){c.showNodes(e,i,d)},i.showNode=function(i,d){i&&c.showNodes(e,[i],d)},i.hideNodes=function(i,d){c.hideNodes(e,i,d)},i.hideNode=function(i,d){i&&c.hideNodes(e,[i],d)};var d=i.checkNode;d&&(i.checkNode=function(e){e&&e.isHidden||d.apply(i,arguments)})},o={initHideForExCheck:function(e,i){i.isHidden&&e.check&&e.check.enable&&("undefined"==typeof i._nocheck&&(i._nocheck=!!i.nocheck,i.nocheck=!0),i.check_Child_State=-1,c.repairParentChkClassWithSelf&&c.repairParentChkClassWithSelf(e,i))},initShowForExCheck:function(e,i){if(!i.isHidden&&e.check&&e.check.enable){if("undefined"!=typeof i._nocheck&&(i.nocheck=i._nocheck,delete i._nocheck),c.setChkClass){var d=l(i,N.id.CHECK,e);c.setChkClass(e,d,i)}c.repairParentChkClassWithSelf&&c.repairParentChkClassWithSelf(e,i)}}},s={clearOldFirstNode:function(e,i){for(var d=i.getNextNode();d;){if(d.isFirstNode){d.isFirstNode=!1,c.setNodeLineIcos(e,d);break}if(d.isLastNode)break;d=d.getNextNode()}},clearOldLastNode:function(e,i,d){for(var t=i.getPreNode();t;){if(t.isLastNode){t.isLastNode=!1,d&&c.setNodeLineIcos(e,t);break}if(t.isFirstNode)break;t=t.getPreNode()}},makeDOMNodeMainBefore:function(e,i,d){e.push("<li ",d.isHidden?"style='display:none;' ":"","id='",d.tId,"' class='",N.className.LEVEL,d.level,"' tabindex='0' hidefocus='true' treenode>")},showNode:function(e,i){i.isHidden=!1,h.initShowForExCheck(e,i),l(i,e).show()},showNodes:function(e,i,d){if(i&&0!=i.length){var t,o,s={};for(t=0,o=i.length;o>t;t++){var n=i[t];if(!s[n.parentTId]){var r=n.getParentNode();s[n.parentTId]=null===r?h.getRoot(e):n.getParentNode()}c.showNode(e,n,d)}for(var a in s){var N=s[a][e.data.key.children];c.setFirstNodeForShow(e,N),c.setLastNodeForShow(e,N)}}},hideNode:function(e,i){i.isHidden=!0,i.isFirstNode=!1,i.isLastNode=!1,h.initHideForExCheck(e,i),c.cancelPreSelectedNode(e,i),l(i,e).hide()},hideNodes:function(e,i,d){if(i&&0!=i.length){var t,o,s={};for(t=0,o=i.length;o>t;t++){var n=i[t];if((n.isFirstNode||n.isLastNode)&&!s[n.parentTId]){var r=n.getParentNode();s[n.parentTId]=null===r?h.getRoot(e):n.getParentNode()}c.hideNode(e,n,d)}for(var a in s){var N=s[a][e.data.key.children];c.setFirstNodeForHide(e,N),c.setLastNodeForHide(e,N)}}},setFirstNode:function(e,i){var d=e.data.key.children,t=i[d].length;t>0&&!i[d][0].isHidden?i[d][0].isFirstNode=!0:t>0&&c.setFirstNodeForHide(e,i[d])},setLastNode:function(e,i){var d=e.data.key.children,t=i[d].length;t>0&&!i[d][0].isHidden?i[d][t-1].isLastNode=!0:t>0&&c.setLastNodeForHide(e,i[d])},setFirstNodeForHide:function(e,i){var d,t,o;for(t=0,o=i.length;o>t&&(d=i[t],!d.isFirstNode);t++){if(!d.isHidden&&!d.isFirstNode){d.isFirstNode=!0,c.setNodeLineIcos(e,d);break}d=null}return d},setFirstNodeForShow:function(e,i){var d,t,o,s,n;for(t=0,o=i.length;o>t;t++){if(d=i[t],!s&&!d.isHidden&&d.isFirstNode){s=d;break}if(s||d.isHidden||d.isFirstNode){if(s&&d.isFirstNode){d.isFirstNode=!1,n=d,c.setNodeLineIcos(e,d);break}d=null}else d.isFirstNode=!0,s=d,c.setNodeLineIcos(e,d)}return{"new":s,old:n}},setLastNodeForHide:function(e,i){var d,t;for(t=i.length-1;t>=0&&(d=i[t],!d.isLastNode);t--){if(!d.isHidden&&!d.isLastNode){d.isLastNode=!0,c.setNodeLineIcos(e,d);break}d=null}return d},setLastNodeForShow:function(e,i){var d,t,o,s;for(t=i.length-1;t>=0;t--){if(d=i[t],!o&&!d.isHidden&&d.isLastNode){o=d;break}if(o||d.isHidden||d.isLastNode){if(o&&d.isLastNode){d.isLastNode=!1,s=d,c.setNodeLineIcos(e,d);break}d=null}else d.isLastNode=!0,o=d,c.setNodeLineIcos(e,d)}return{"new":o,old:s}}},n={view:s,data:o};e.extend(!0,e.fn.zTree._z,n);var r=e.fn.zTree,a=r._z.tools,N=r.consts,c=r._z.view,h=r._z.data,l=(r._z.event,a.$);h.addInitNode(i),h.addBeforeA(d),h.addZTreeTools(t);var f=h.initNode;h.initNode=function(e,i,d,t,o,s,n){var r=t?t:h.getRoot(e),a=r[e.data.key.children];h.tmpHideFirstNode=c.setFirstNodeForHide(e,a),h.tmpHideLastNode=c.setLastNodeForHide(e,a),n&&(c.setNodeLineIcos(e,h.tmpHideFirstNode),c.setNodeLineIcos(e,h.tmpHideLastNode)),o=h.tmpHideFirstNode===d,s=h.tmpHideLastNode===d,f&&f.apply(h,arguments),n&&s&&c.clearOldLastNode(e,d,n)};var u=h.makeChkFlag;u&&(h.makeChkFlag=function(e,i){i&&i.isHidden||u.apply(h,arguments)});var k=h.getTreeCheckedNodes;k&&(h.getTreeCheckedNodes=function(e,i){if(i&&i.length>0){var d=i[0].getParentNode();if(d&&d.isHidden)return[]}return k.apply(h,arguments)});var F=h.getTreeChangeCheckedNodes;F&&(h.getTreeChangeCheckedNodes=function(e,i){if(i&&i.length>0){var d=i[0].getParentNode();if(d&&d.isHidden)return[]}return F.apply(h,arguments)});var H=c.expandCollapseSonNode;H&&(c.expandCollapseSonNode=function(e,i){i&&i.isHidden||H.apply(c,arguments)});var g=c.setSonNodeCheckBox;g&&(c.setSonNodeCheckBox=function(e,i){i&&i.isHidden||g.apply(c,arguments)});var p=c.repairParentChkClassWithSelf;p&&(c.repairParentChkClassWithSelf=function(e,i){i&&i.isHidden||p.apply(c,arguments)})}(jQuery);