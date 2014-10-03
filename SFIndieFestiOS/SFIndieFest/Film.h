//
//  Film.h
//  SFIndieFest
//
//  Created by eric ringer on 9/24/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <EventKit/EventKit.h>
@interface Film : NSObject
@property (nonatomic, retain)NSString *filmTitle;
@property (nonatomic, retain)NSString *filmImageName;
@property (nonatomic, retain)NSString *dateTime;
@property (nonatomic, retain)NSDate *convertedDate;
@property (nonatomic, retain)NSString *synopsis;
@property (nonatomic, retain)NSString *trailerUrl;
@property (nonatomic, retain)NSString *filmUid;
@property (nonatomic, retain)EKReminder *reminder;

-(NSString *)getPurchaseUrl;
+(NSArray *)getStaticFilms;

@end
