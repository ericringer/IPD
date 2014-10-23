//
//  FilmTableViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#include <EventKit/EventKit.h>

@interface FilmTableViewController : UITableViewController
@property (strong, nonatomic) EKEventStore *eventStore;
@end
